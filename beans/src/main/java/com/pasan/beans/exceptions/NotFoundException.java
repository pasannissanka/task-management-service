package com.pasan.beans.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String additionalInfo;
    public NotFoundException(String message, String additionalInfo) {
        super(message);
        this.additionalInfo = additionalInfo;
    }

}
