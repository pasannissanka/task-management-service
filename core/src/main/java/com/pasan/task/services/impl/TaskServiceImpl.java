package com.pasan.task.services.impl;

import com.pasan.task.beans.dtos.TaskDto;
import com.pasan.task.beans.entities.Task;
import com.pasan.task.beans.exceptions.NotFoundException;
import com.pasan.task.repositories.TaskRepository;
import com.pasan.task.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Task service implementation
 */
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
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
                        .completed(taskDto.isCompleted())
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
        return taskRepository.findAll()
                .stream()
                .map(TaskDto::from)
                .toList();
    }
}
