package com.Emazon.Stock.domain.utilities.Exceptions;

public class ArticleAlreadyExistsDomainException extends RuntimeException {
    public ArticleAlreadyExistsDomainException(String message) {
        super(message);
    }
}