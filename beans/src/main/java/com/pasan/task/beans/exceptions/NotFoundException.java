package com.pasan.task.beans.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NotFoundException extends RuntimeException {
    private final String additionalInfo;

    public NotFoundException(String message, String additionalInfo) {
        super(message);
        this.additionalInfo = additionalInfo;
    }
}
