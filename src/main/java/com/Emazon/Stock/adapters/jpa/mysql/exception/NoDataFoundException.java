package com.Emazon.Stock.adapters.jpa.mysql.exception;


import com.Emazon.Stock.adapters.utilities.ExceptionConstants;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException() {
        super(ExceptionConstants.NO_DATA_FOUND_MESSAGE);
    }
}