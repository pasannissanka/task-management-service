package com.pasan.task.controller;

import com.pasan.task.TaskManagementApplication;
import com.pasan.task.beans.constants.ErrorMessages;
import com.pasan.task.beans.constants.SuccessMessages;
import com.pasan.task.data.RequestPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TaskManagementApplication.class)
public class TaskControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeClass
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test(priority = 1)
    public void test_should_get_all_tasks() throws Exception {
        mockMvc.perform(get("/v1/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value(SuccessMessages.TASKS_FETCHED_SUCCESSFULLY))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test(priority = 2)
    public void test_should_get_task() throws Exception {
        long id = 1L;
        mockMvc.perform(get("/v1/tasks/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value(SuccessMessages.TASK_FETCHED_SUCCESSFULLY))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").value(id));
    }

    @Test
    public void test_should_create_task() throws Exception {
        mockMvc.perform(
                    post("/v1/tasks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(RequestPayload.CREATE_TASK_REQUEST_SUCCESS)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value(SuccessMessages.TASK_CREATED_SUCCESSFULLY))
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    public void test_should_create_task_fail_with_validation_error() throws Exception {
        mockMvc.perform(
                        post("/v1/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(RequestPayload.CREATE_TASK_REQUEST_MISSING_NAME)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(ErrorMessages.VALIDATION_ERROR))
                .andExpect(jsonPath("$.additionalInfo").exists())
                .andExpect(jsonPath("$.additionalInfo").value("{name=Name is required}"));
    }

    @Test
    public void test_should_update_task() throws Exception {
        long id = 1L;
        mockMvc.perform(
                        put("/v1/tasks/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(RequestPayload.UPDATE_TASK_REQUEST_SUCCESS)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value(SuccessMessages.TASK_UPDATED_SUCCESSFULLY))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").value(id))
                .andExpect(jsonPath("$.data.name").value("Test Updated"));
    }

    @Test
    public void test_should_update_task_fail_with_validation_exception() throws Exception {
        long id = 1L;
        mockMvc.perform(
                        put("/v1/tasks/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(RequestPayload.UPDATE_TASK_REQUEST_VALIDATION_ERROR)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(ErrorMessages.VALIDATION_ERROR))
                .andExpect(jsonPath("$.additionalInfo").exists())
                .andExpect(jsonPath("$.additionalInfo").value("{name=Name is required}"));
    }

    @Test
    public void test_should_update_task_fail_with_not_found_exception() throws Exception {
        long id = 1000L;
        mockMvc.perform(
                        put("/v1/tasks/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(RequestPayload.UPDATE_TASK_REQUEST_SUCCESS)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(ErrorMessages.TASK_NOT_FOUND))
                .andExpect(jsonPath("$.additionalInfo").exists());
    }

    @Test
    public void test_should_delete_task() throws Exception {
        long id = 2L;
        mockMvc.perform(
                        delete("/v1/tasks/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value(SuccessMessages.TASK_DELETED_SUCCESSFULLY))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    public void test_should_delete_task_fail_with_not_found_exception() throws Exception {
        long id = 10000L;
        mockMvc.perform(
                        delete("/v1/tasks/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(ErrorMessages.TASK_NOT_FOUND))
                .andExpect(jsonPath("$.additionalInfo").exists());
    }
}
