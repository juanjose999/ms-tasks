package com.task_ms.repository;

import com.task_ms.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {

    Task saveTask(Task task);

    @Query("SELECT t FROM Task t WHERE t.id_user = :id")
    List<Task> findAllTaskByIdUser(@Param("id") String id);

    @Query("SELECT t FROM Task t WHERE t.id_user = :idUser AND t.id = :idTask")
    Optional<Task> findTaskByIdTaskAndIdUser(@Param("idUser") String idUser, @Param("idTask") Integer idTask);

    Task update(Integer idTask, String idUser, Task taskToUpdate);

    Boolean deleteTaskByIdTaskAndIdUser(Integer idTask, String idUser);

}
