package com.Emazon.Stock.adapters.jpa.mysql.exception;

import com.Emazon.Stock.adapters.utilities.ExceptionConstants;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException() {
        super(ExceptionConstants.ELEMENT_NOT_FOUND_MESSAGE);
    }
}