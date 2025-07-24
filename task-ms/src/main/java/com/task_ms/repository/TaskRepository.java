package com.task_ms.repository;

import com.task_ms.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public class TaskRepository {

    private final ITaskRepository taskRepository;

    public TaskRepository(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAllTaskByIdUser(String id){
        return taskRepository.findAllTaskByIdUser(id);
    }

    public Optional<Task> findTaskByIdTaskAndIdUser(String idUser,  Integer idTask){
        return taskRepository.findTaskByIdTaskAndIdUser(idUser, idTask);
    }

    public Task update(Integer idTask, String idUser, Task taskToUpdate){
        Task findTask = taskRepository.findTaskByIdTaskAndIdUser(idUser,idTask).orElseThrow(()->new RuntimeException("No se encontro la tarea."));
        findTask.setTitle(taskToUpdate.getTitle());
        findTask.setContent(taskToUpdate.getContent());
        return taskRepository.save(findTask);
    }

    public Boolean deleteTaskByIdTaskAndIdUser(Integer idTask, String idUser){
        Task findTask = taskRepository.findTaskByIdTaskAndIdUser(idUser,idTask).orElseThrow(()->new RuntimeException("No se encontro la tarea."));
        if(findTask.getId().equals(idTask)){
            taskRepository.delete(findTask);
            return true;
        }
        return false;
    }

}
