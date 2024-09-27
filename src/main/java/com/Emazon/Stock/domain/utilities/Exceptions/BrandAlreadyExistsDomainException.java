package com.Emazon.Stock.domain.utilities.Exceptions;

public class BrandAlreadyExistsDomainException extends RuntimeException {
    public BrandAlreadyExistsDomainException(String message) {
         super(message);
    }
}
