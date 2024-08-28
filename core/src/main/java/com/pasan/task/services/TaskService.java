package com.pasan.task.services;

import com.pasan.task.beans.dtos.TaskDto;

import java.util.List;

/**
 * Task service
 */
public interface TaskService {

    /**
     * Create a new task
     *
     * @param taskDto task dto
     * @return created task dto
     */
    TaskDto createTask(TaskDto taskDto);

    /**
     * Update a task
     *
     * @param taskId task id
     * @param taskDto task dto
     * @return updated task dto
     */
    TaskDto updateTask(Long taskId, TaskDto taskDto);

    /**
     * Get a task
     *
     * @param taskId task id
     * @return task dto
     */
    TaskDto getTask(Long taskId);

    /**
     * Delete a task
     *
     * @param taskId task id
     */
    Boolean deleteTask(Long taskId);

    /**
     * Get all tasks
     *
     * @return list of task dtos
     */
    List<TaskDto> getAllTasks();
}
