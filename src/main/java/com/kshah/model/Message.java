package com.kshah.model;

import java.util.Date;

public final class Message {

    private final String version;

    private final String message;

    private final Date messageCreationDate;

    public Message(String version, String message, Date messageCreationDate) {
        this.version = version;
        this.message = message;
        this.messageCreationDate = messageCreationDate;
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
