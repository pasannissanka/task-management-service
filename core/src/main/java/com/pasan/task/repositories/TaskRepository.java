package com.pasan.task.repositories;

import com.pasan.task.beans.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Task repository
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
