package com.Emazon.Stock.adapters.jpa.mysql.exception;

import com.Emazon.Stock.adapters.utilities.ExceptionConstants;

public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException() {
        super(ExceptionConstants.BRAND_ALREADY_EXISTS_MESSAGE);
    }
}