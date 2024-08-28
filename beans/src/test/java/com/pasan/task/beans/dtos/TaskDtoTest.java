package com.pasan.task.beans.dtos;

import com.pasan.task.beans.entities.Task;
import com.pasan.task.beans.enums.Priority;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for TaskDto
 */
public class TaskDtoTest {

    /**
     * Test conversion of Task entity to TaskDto
     */
    @Test
    public void should_convert_TaskEntity_to_Dto()
    {
        Task task = createTask();

        TaskDto taskDto = TaskDto.from(task);

        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getName(), taskDto.getName());
        assertEquals(task.getDescription(), taskDto.getDescription());
        assertEquals(Priority.valueOf(task.getPriority()), taskDto.getPriority());
        assertEquals(task.isCompleted(), taskDto.isCompleted());
    }

    private Task createTask() {
        Random rand = new Random();

        int id = rand.nextInt();
        Task task = new Task();
        task.setId(id);
        task.setName("Task 1");
        task.setDescription("Task 1 Description");
        task.setPriority("HIGH");
        return task;
    }
}
