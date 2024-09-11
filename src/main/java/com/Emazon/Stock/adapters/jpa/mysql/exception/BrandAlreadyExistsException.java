package com.Emazon.Stock.adapters.jpa.mysql.exception;

public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException() {
        super("Brand already exists");
    }
}