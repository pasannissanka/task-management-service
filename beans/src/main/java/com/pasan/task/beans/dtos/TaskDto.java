package com.pasan.task.beans.dtos;

import com.pasan.task.beans.entities.Task;
import com.pasan.task.beans.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TaskDto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Completed status is required")
    private Boolean completed;

    /**
     * Convert Task entity to TaskDto
     * @param task Task entity
     * @return TaskDto
     */
    public static TaskDto from(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .priority(Priority.valueOf(task.getPriority()))
                .completed(task.isCompleted())
                .build();
    }
}
