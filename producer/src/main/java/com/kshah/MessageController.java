package com.kshah;

import com.kshah.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
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

    private MessageSource messageSource;

    @Autowired
    public MessageController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestBody Message message) {
        org.springframework.messaging.Message<Message> message1 = MessageBuilder.withPayload(message).build();
        messageSource.outbound().send(message1);
        return new ResponseEntity(HttpStatus.OK);
    }

}
