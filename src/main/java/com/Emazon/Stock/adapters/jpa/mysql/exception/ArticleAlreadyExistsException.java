package com.Emazon.Stock.adapters.jpa.mysql.exception;

public class ArticleAlreadyExistsException extends RuntimeException {
    public ArticleAlreadyExistsException(String message) {

        super(message);
    }
}