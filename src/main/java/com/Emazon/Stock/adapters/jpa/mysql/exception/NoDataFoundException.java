package com.Emazon.Stock.adapters.jpa.mysql.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException() {
        super("No data found");
    }

    public NoDataFoundException(String message) {
        super(message);
    }

}