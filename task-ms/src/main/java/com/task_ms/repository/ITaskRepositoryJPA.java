package com.task_ms.repository;

import com.task_ms.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ITaskRepositoryJPA extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.id_user = :id")
    List<Task> findAllTaskByIdUser(@Param("id") String id);

    @Query("SELECT t FROM Task t WHERE t.id_user = :idUser AND t.id = :idTask")
    Optional<Task> findTaskByIdTaskAndIdUser(@Param("idUser") String idUser, @Param("idTask") Integer idTask);
}
