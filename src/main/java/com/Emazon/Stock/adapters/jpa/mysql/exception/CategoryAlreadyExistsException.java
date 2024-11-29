package com.Emazon.Stock.adapters.jpa.mysql.exception;

import com.Emazon.Stock.adapters.utilities.ExceptionConstants;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super(ExceptionConstants.CATEGORY_ALREADY_EXISTS_MESSAGE);
    }
}