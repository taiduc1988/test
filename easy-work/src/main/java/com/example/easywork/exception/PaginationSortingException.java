package com.example.easywork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class PaginationSortingException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 9153386366344360716L;
    private String message;

    public PaginationSortingException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
