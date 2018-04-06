package com.kshah.model;

import java.util.Date;

public final class Message {

    private String version;

    private String message;

    private Date messageCreationDate;

    public Message(){}

    public Message(String version, String message, Date messageCreationDate) {
        this.version = version;
        this.message = message;
        this.messageCreationDate = messageCreationDate;
    }


    public String getVersion() {
        return version;
    }

    public String getMessage() {
        return message;
    }

    public Date getMessageCreationDate() {
        return messageCreationDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "version='" + version + '\'' +
                ", message='" + message + '\'' +
                ", messageCreationDate=" + messageCreationDate +
                '}';
    }

}
