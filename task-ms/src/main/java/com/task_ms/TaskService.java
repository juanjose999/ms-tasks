package com.task_ms;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task findTaskByIdAndIdUser(String idUser, Integer idTask) {
        return taskRepository.findTaskByIdUser(idUser, idTask).orElseThrow(() -> new RuntimeException("No se encontraron registros"));
    }

    public List<Task> findAllByIdUser(String idUser) {
        return taskRepository.findById_user(idUser);
    }

    public Task update(Integer idTask, String idUser, Task task) {
        Task oldTask = findTaskByIdAndIdUser(idUser, idTask);
        oldTask.setTitle(task.getTitle());
        oldTask.setContent(task.getContent());
        return taskRepository.save(oldTask);
    }

    public boolean deleteTaskByIdTaskAndIdUser(Integer idTask, String idUser) {
        Task task = findTaskByIdAndIdUser(idUser, idTask);
        if(task != null){
            taskRepository.delete(task);
            return true;
        }
        return false;
    }

}


