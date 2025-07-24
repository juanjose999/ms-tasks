package com.task_ms.service;

import com.task_ms.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    Task saveTask(Task task);
    List<Task> findAllTaskByIdUser(@Param("id") String id);
    Optional<Task> findTaskByIdTaskAndIdUser(@Param("idUser") String idUser, @Param("idTask") Integer idTask);
    Task update(Integer idTask, String idUser, Task taskToUpdate);
    Boolean deleteTaskByIdTaskAndIdUser(Integer idTask, String idUser);
}
