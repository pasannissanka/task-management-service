package com.pasan.task.beans.dtos;

import com.pasan.task.beans.entities.Task;
import com.pasan.task.beans.enums.Priority;
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

    private String name;

    private String description;

    private Priority priority;

    private boolean completed;

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
