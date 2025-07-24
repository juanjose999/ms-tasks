package com.task_ms.service;

import com.task_ms.entity.Task;
import com.task_ms.repository.ITaskRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService{

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }
    public List<Task> findAllTaskByIdUser(@Param("id") String id){
        return taskRepository.findAllTaskByIdUser(id);
    }
    public Optional<Task> findTaskByIdTaskAndIdUser(String idUser,  Integer idTask){
        return taskRepository.findTaskByIdTaskAndIdUser(idUser, idTask);
    }
    public Task update(Integer idTask, String idUser, Task taskToUpdate){
        return taskRepository.update(idTask, idUser, taskToUpdate);
    }
    public Boolean deleteTaskByIdTaskAndIdUser(Integer idTask, String idUser){
        return taskRepository.deleteTaskByIdTaskAndIdUser(idTask, idUser);
    }

}


