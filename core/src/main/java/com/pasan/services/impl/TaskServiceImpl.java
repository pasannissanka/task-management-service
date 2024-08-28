package com.pasan.services.impl;

import com.pasan.beans.dtos.TaskDto;
import com.pasan.beans.entities.Task;
import com.pasan.beans.exceptions.NotFoundException;
import com.pasan.repositories.TaskRepository;
import com.pasan.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Task service implementation
 */
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * @param taskDto task dto
     * @return
     */
    @Override
    public TaskDto createTask(TaskDto taskDto) {
        logger.info("Creating new task with data [{}]", taskDto);

        Task task = taskRepository.save(
                Task.builder()
                        .name(taskDto.getName())
                        .description(taskDto.getDescription())
                        .priority(taskDto.getPriority().name())
                        .isCompleted(taskDto.isCompleted())
                        .build()
        );

        logger.info("Task created with id [{}]", task.getId());
        return TaskDto.from(task);
    }

    /**
     * @param taskId  task id
     * @param taskDto task dto
     * @return
     */
    @Override
    public TaskDto updateTask(Long taskId, TaskDto taskDto) {
        logger.info("Updating task with id [{}]", taskId);

        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new NotFoundException("Task not found", String.format("Task with id [%d] not found", taskId))
        );
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority().name());
        task.setCompleted(taskDto.isCompleted());

        logger.info("Task updated with id [{}]", task.getId());
        return TaskDto.from(taskRepository.save(task));
    }

    /**
     * @param taskId task id
     * @return
     */
    @Override
    public TaskDto getTask(Long taskId) {
        logger.info("Getting task with id [{}]", taskId);

        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new NotFoundException("Task not found", String.format("Task with id [%d] not found", taskId))
        );

        logger.info("Task found [{}]", task);
        return TaskDto.from(task);
    }

    /**
     * @param taskId task id
     * @return
     */
    @Override
    public boolean deleteTask(Long taskId) {
        logger.info("Deleting task with id [{}]", taskId);

        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new NotFoundException("Task not found", String.format("Task with id [%d] not found", taskId))
        );

        taskRepository.delete(task);
        logger.info("Task deleted with id [{}]", taskId);
        return true;
    }

    /**
     * @return
     */
    @Override
    public List<TaskDto> getAllTasks() {
        logger.info("Getting all tasks");
        return taskRepository.findAll()
                .stream()
                .map(TaskDto::from)
                .toList();
    }
}
