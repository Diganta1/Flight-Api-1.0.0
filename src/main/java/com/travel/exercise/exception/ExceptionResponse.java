package com.travel.exercise.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;

    public ExceptionResponse(Date timestamp, String message) {
        super();
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
