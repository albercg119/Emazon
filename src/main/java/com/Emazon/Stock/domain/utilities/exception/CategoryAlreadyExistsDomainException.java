package com.Emazon.Stock.domain.utilities.exception;

public class CategoryAlreadyExistsDomainException extends RuntimeException {
    public CategoryAlreadyExistsDomainException(String message) {
        super(message);
    }
}