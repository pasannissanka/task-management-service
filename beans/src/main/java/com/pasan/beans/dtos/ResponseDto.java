package com.pasan.beans.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto<T> {
    private T data;
    private boolean success;
    private String message;
    private String additionalInfo;

    public static <T> ResponseDto<T> success(T data, String message) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setData(data);
        responseDto.setMessage(message);
        return responseDto;
    }

    public static ResponseDto<?> error(String message, String additionalInfo) {
        ResponseDto<?> responseDto = new ResponseDto<>();
        responseDto.setSuccess(false);
        responseDto.setMessage(message);
        responseDto.setAdditionalInfo(additionalInfo);
        return responseDto;
    }
}
