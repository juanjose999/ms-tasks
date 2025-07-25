package com.task_ms.controller;

import com.task_ms.entity.Task;
import com.task_ms.exception.TaskException;
import com.task_ms.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @GetMapping("/allByIdUser/{idUser}")
    public ResponseEntity<?> getAllTasksByIdUser(@PathVariable String idUser) {
        return ResponseEntity.ok(taskService.findAllTaskByIdUser(idUser));
    }

    @GetMapping("{idTask}/idUser/{idUser}")
    public ResponseEntity<?> getTaskByIdTaskAndIdUser(@PathVariable String idUser, @PathVariable Integer idTask) {
        return ResponseEntity.ok(taskService.findTaskByIdTaskAndIdUser(idUser, idTask));
    }

    @PutMapping("{idTask}/idUser/{idUser}")
    public ResponseEntity<?> updateTaskByIdTaskAndIdUser(@PathVariable String idUser, @PathVariable Integer idTask ,@RequestBody Task task) throws TaskException {
        return ResponseEntity.ok(taskService.update(idTask, idUser, task));
    }

    @DeleteMapping("{idTask}/idUser/{idUser}")
    public ResponseEntity<?> deleteTaskByTaskAndIdUser(@PathVariable Integer idTask, @PathVariable String idUser) throws TaskException {
        return taskService.deleteTaskByIdTaskAndIdUser(idTask,idUser) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
