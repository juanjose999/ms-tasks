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

    public Task findTaskByIdUser(Integer idUser){
        return taskRepository.findTaskByIdUser(idUser).orElseThrow(() -> new RuntimeException("No se encontraron registros"));
    }

    public List<Task> findAllByIdUser(Integer idUser) {
        return taskRepository.findById_user(idUser);
    }

    public Task update(Task task, Integer idUser) {
        Task oldTask = findTaskByIdUser(idUser);
        oldTask.setTitle(task.getTitle());
        oldTask.setContent(task.getContent());
        return taskRepository.save(oldTask);
    }

    public boolean deleteTaskByIdTaskAndIdUser(Integer idTask, Integer idUser) {
        Task task = findTaskByIdUser(idUser);
        if(task != null){
            taskRepository.delete(task);
            return true;
        }
        return false;
    }

}


