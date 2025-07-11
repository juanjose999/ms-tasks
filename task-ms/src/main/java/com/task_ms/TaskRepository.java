package com.task_ms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.id_user = :id")
    List<Task> findById_user(@Param("id") String id);

    @Query("SELECT t FROM Task t WHERE t.id_user = :idUser AND t.id = :idTask")
    Optional<Task> findTaskByIdUser(@Param("idUser") String idUser, @Param("idTask") Integer idTask);
}
