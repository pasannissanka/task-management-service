package com.pasan.task.data;

public class RequestPayload {
    public static final String CREATE_TASK_REQUEST_SUCCESS = """
            {
                "name": "Test",
                "description": "Test",
                "priority": "MEDIUM",
                "completed": false
            }""";

    public static final String CREATE_TASK_REQUEST_MISSING_NAME = """
            {
                "description": "Test",
                "priority": "MEDIUM",
                "completed": false
            }""";
    public static final String UPDATE_TASK_REQUEST_SUCCESS = """
            {
                "name": "Test Updated",
                "description": "Test Updated",
                "priority": "MEDIUM",
                "completed": false
            }""";
    public static final String UPDATE_TASK_REQUEST_VALIDATION_ERROR = """
            {
                "description": "Test Updated",
                "priority": "MEDIUM",
                "completed": false
            }""";
}
