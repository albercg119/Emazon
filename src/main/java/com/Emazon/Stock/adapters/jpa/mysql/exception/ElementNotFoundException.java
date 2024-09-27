package com.Emazon.Stock.adapters.jpa.mysql.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException() {
        super("Element not found");
    }

    public ElementNotFoundException(String message) {
        super(message);
    }
}