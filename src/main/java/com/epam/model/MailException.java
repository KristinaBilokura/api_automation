package com.epam.model;

import javax.ws.rs.core.Response.Status;

public class MailException extends RuntimeException{

    private String message;
    private Status status;

    public MailException(String message) {
        this.message = message;
    }
    public MailException(String message,Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }


}
