package com.ramjee.blogAppPractise.payload;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String path;

    public ErrorDetails(Date timestamp, String message, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
