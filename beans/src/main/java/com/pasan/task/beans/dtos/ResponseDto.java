package com.pasan.task.beans.dtos;

import lombok.*;

@Getter
@Setter
@Builder
public class ResponseDto<T> {
    private T data;
    private boolean success;
    private String message;
    private String additionalInfo;

    public static <T> ResponseDto<T> success(T data, String message) {
        return ResponseDto.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .build();
    }

    public static ResponseDto<?> error(String message, String additionalInfo) {
        return ResponseDto.builder()
                .success(false)
                .message(message)
                .additionalInfo(additionalInfo)
                .build();
    }
}
