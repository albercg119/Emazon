package com.emzon.user.configuration.exceptionhandler;

public class ExceptionResponse {
    private String message;
    private int statusCode;
    private String description;

    public ExceptionResponse(String message, int statusCode, String description) {
        this.message = message;
        this.statusCode = statusCode;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
