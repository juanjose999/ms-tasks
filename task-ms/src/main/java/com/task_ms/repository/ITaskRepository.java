package com.task_ms.repository;

import com.task_ms.entity.Task;
import com.task_ms.exception.TaskException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskRepository  {

    Task saveTask(Task task);

    List<Task> findAllTaskByIdUser(String id);

    Optional<Task> findTaskByIdTaskAndIdUser(String idUser, Integer idTask);

    Task update(Integer idTask, String idUser, Task taskToUpdate) throws TaskException;

    Boolean deleteTaskByIdTaskAndIdUser(Integer idTask, String idUser) throws TaskException;

}
