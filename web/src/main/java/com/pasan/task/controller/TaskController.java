package com.pasan.task.controller;

import com.pasan.task.beans.dtos.ResponseDto;
import com.pasan.task.beans.dtos.TaskDto;
import com.pasan.task.services.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<TaskDto>>> getAllTasks() {
        logger.info("Fetching all tasks");
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(ResponseDto.success(tasks, "Tasks fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<ResponseDto<TaskDto>> createTask(@Valid @RequestBody() TaskDto taskDto) {
        logger.info("Creating new task with data [{}]", taskDto);
        TaskDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.ok(ResponseDto.success(createdTask, "Task created successfully"));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseDto<TaskDto>> getTaskById(@PathVariable("taskId") Long taskId) {
        logger.info("Fetching task with id [{}]", taskId);
        TaskDto task = taskService.getTask(taskId);
        return ResponseEntity.ok(ResponseDto.success(task, "Task fetched successfully"));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<ResponseDto<TaskDto>> updateTask(@PathVariable("taskId") Long taskId, @Valid @RequestBody TaskDto taskDto) {
        logger.info("Updating task with id [{}]", taskId);
        TaskDto updatedTask = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(ResponseDto.success(updatedTask, "Task updated successfully"));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseDto<Boolean>> deleteTask(@PathVariable("taskId") Long taskId) {
        logger.info("Deleting task with id [{}]", taskId);
        Boolean success = taskService.deleteTask(taskId);
        return ResponseEntity.ok(ResponseDto.success(success, "Task deleted successfully"));
    }
}
