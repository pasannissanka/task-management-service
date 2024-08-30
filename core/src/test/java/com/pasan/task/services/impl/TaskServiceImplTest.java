package com.pasan.task.services.impl;

import com.pasan.task.beans.constants.ErrorMessages;
import com.pasan.task.beans.dtos.TaskDto;
import com.pasan.task.beans.entities.Task;
import com.pasan.task.beans.enums.Priority;
import com.pasan.task.beans.exceptions.NotFoundException;
import com.pasan.task.repositories.TaskRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

/**
 * Task service Unit tests
 */
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeTest
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_CreateTask() {
        Task task = createTask("Task 1", "Task 1 description", Priority.HIGH, false);
        TaskDto taskDto = TaskDto.from(task);

        when(taskRepository.save(any())).thenReturn(task);

        TaskDto createdTaskDto = taskService.createTask(taskDto);

        assertNotNull(createdTaskDto);
        assertEquals(createdTaskDto.getId(), taskDto.getId());
        assertEquals(createdTaskDto.getName(), taskDto.getName());
        assertEquals(createdTaskDto.getDescription(), taskDto.getDescription());
        assertEquals(createdTaskDto.getPriority(), taskDto.getPriority());
        assertEquals(createdTaskDto.getCompleted(), taskDto.getCompleted());
    }

    @Test
    public void should_UpdateTask_success() {
        Task task = createTask("Task 1", "Task 1 description", Priority.HIGH, false);
        TaskDto taskDto = TaskDto.from(task);
        taskDto.setCompleted(true);
        taskDto.setPriority(Priority.LOW);

        when(taskRepository.findById(task.getId())).thenReturn(java.util.Optional.of(task));
        when(taskRepository.save(any())).then(returnsFirstArg());

        TaskDto updatedTaskDto = taskService.updateTask(task.getId(), taskDto);

        assertNotNull(updatedTaskDto);
        assertEquals(updatedTaskDto.getId(), taskDto.getId());
        assertEquals(updatedTaskDto.getName(), taskDto.getName());
        assertEquals(updatedTaskDto.getDescription(), taskDto.getDescription());
        assertEquals(updatedTaskDto.getPriority(), taskDto.getPriority());
        assertEquals(updatedTaskDto.getCompleted(), taskDto.getCompleted());
    }

    @Test(
            expectedExceptions = NotFoundException.class,
            expectedExceptionsMessageRegExp = ErrorMessages.TASK_NOT_FOUND
    )
    public void should_UpdateTask_throw_NotFoundException() {
        Task task = createTask("Task 1", "Task 1 description", Priority.HIGH, false);
        TaskDto taskDto = TaskDto.from(task);

        when(taskRepository.findById(task.getId())).thenReturn(java.util.Optional.empty());

        taskService.updateTask(task.getId(), taskDto);
    }

    @Test
    public void should_GetTask_success() {
        Task task = createTask("Task 1", "Task 1 description", Priority.HIGH, false);
        TaskDto taskDto = TaskDto.from(task);

        when(taskRepository.findById(task.getId())).thenReturn(java.util.Optional.of(task));

        TaskDto fetchedTaskDto = taskService.getTask(task.getId());

        assertNotNull(fetchedTaskDto);
        assertEquals(fetchedTaskDto.getId(), taskDto.getId());
        assertEquals(fetchedTaskDto.getName(), taskDto.getName());
        assertEquals(fetchedTaskDto.getDescription(), taskDto.getDescription());
        assertEquals(fetchedTaskDto.getPriority(), taskDto.getPriority());
        assertEquals(fetchedTaskDto.getCompleted(), taskDto.getCompleted());
    }

    @Test(
            expectedExceptions = NotFoundException.class,
            expectedExceptionsMessageRegExp = ErrorMessages.TASK_NOT_FOUND
    )
    public void should_GetTask_throw_NotFoundException() {
        Task task = createTask("Task 1", "Task 1 description", Priority.HIGH, false);

        when(taskRepository.findById(task.getId())).thenReturn(java.util.Optional.empty());

        taskService.getTask(task.getId());
    }

    @Test
    public void should_DeleteTask_success() {
        Task task = createTask("Task 1", "Task 1 description", Priority.HIGH, false);

        when(taskRepository.findById(task.getId())).thenReturn(java.util.Optional.of(task));

        boolean success = taskService.deleteTask(task.getId());

        assertTrue(success);
    }

    @Test(
            expectedExceptions = NotFoundException.class,
            expectedExceptionsMessageRegExp = ErrorMessages.TASK_NOT_FOUND
    )
    public void should_DeleteTask_throw_NotFoundException() {
        Task task = createTask("Task 1", "Task 1 description", Priority.HIGH, false);

        when(taskRepository.findById(task.getId())).thenReturn(java.util.Optional.empty());

        taskService.deleteTask(task.getId());
    }

    @Test
    public void should_GetAllTasks_success() {
        Task task1 = createTask("Task 1", "Task 1 description", Priority.HIGH, false);
        Task task2 = createTask("Task 2", "Task 2 description", Priority.MEDIUM, true);
        Task task3 = createTask("Task 3", "Task 3 description", Priority.LOW, false);

        when(taskRepository.findAll()).thenReturn(java.util.List.of(task1, task2, task3));

        var tasks = taskService.getAllTasks();

        assertNotNull(tasks);
        assertEquals(tasks.size(), 3);
    }

    private Task createTask(String name, String description, Priority priority, boolean completed) {
        long id = ThreadLocalRandom.current().nextLong(100);
        return Task.builder()
                .id(id)
                .name(name)
                .description(description)
                .priority(priority.name())
                .completed(completed)
                .build();
    }
}
