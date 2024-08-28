package com.pasan.task.beans.dtos;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ResponseDtoTest {

    @Test
    public void testSuccess() {
        ResponseDto<String> responseDto = ResponseDto.success("data", "message");
        assertEquals(responseDto.getData(), "data");
        assertEquals(responseDto.getMessage(), "message");
        assertTrue(responseDto.isSuccess());
    }

    @Test
    public void testError() {
        ResponseDto<?> responseDto = ResponseDto.error("message", "additionalInfo");
        assertEquals(responseDto.getMessage(), "message");
        assertEquals(responseDto.getAdditionalInfo(), "additionalInfo");
        assertFalse(responseDto.isSuccess());
    }
}
