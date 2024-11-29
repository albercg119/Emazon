package com.Emazon.Stock.domain.utilities.Exceptions;

public class CategoryAlreadyExistsDomainException extends RuntimeException {
    public CategoryAlreadyExistsDomainException(String message) {
        super(message);
    }
}
