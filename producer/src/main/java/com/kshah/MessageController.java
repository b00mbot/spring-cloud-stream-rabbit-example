package com.kshah;

import com.kshah.model.Message;
import org.springframework.amqp.rabbit.listener.QueuesNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(MessageSource.class)
@RestController
public class MessageController {

    @Value("${queue.outbound.retry-attempts}")
    private Long retryAttempts;

    @Value("${queue.outbound.timeout}")
    private Long timeout;

    private MessageSource messageSource;

    @Autowired
    public MessageController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestBody Message message) {
        org.springframework.messaging.Message<Message> message1 = MessageBuilder.withPayload(message).build();

        // Will retry however many times set in configuration if message is not delivered
        boolean messageSent = false;
        for(int retries = 0; retries < retryAttempts; retries++) {
            messageSent = messageSource.outbound().send(message1, timeout);
            if(messageSent) {
                break;
            }
        }

        if(!messageSent) {
            throw new QueueUnavailableException("Attempted to send message '" + retryAttempts + "' times but outbound queue could not be reached.");
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
