package com.pasan.services;

import com.pasan.beans.dtos.TaskDto;

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
    public TaskDto createTask(TaskDto taskDto);

    /**
     * Update a task
     *
     * @param taskId task id
     * @param taskDto task dto
     * @return updated task dto
     */
    public TaskDto updateTask(Long taskId, TaskDto taskDto);

    /**
     * Get a task
     *
     * @param taskId task id
     * @return task dto
     */
    public TaskDto getTask(Long taskId);

    /**
     * Delete a task
     *
     * @param taskId task id
     */
    public boolean deleteTask(Long taskId);

    /**
     * Get all tasks
     *
     * @return list of task dtos
     */
    public List<TaskDto> getAllTasks();
}
