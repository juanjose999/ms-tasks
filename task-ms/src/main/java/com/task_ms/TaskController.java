package com.task_ms;

import jakarta.ws.rs.PUT;
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
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.save(task));
    }

    @GetMapping("/allByIdUser/{idUser}")
    public ResponseEntity<?> getAllTasksByIdUser(@PathVariable Integer idUser) {
        return ResponseEntity.ok(taskService.findAllByIdUser(idUser));
    }

    @GetMapping("/idUser/{idUser}")
    public ResponseEntity<?> getTaskByIdUser(@PathVariable Integer idUser) {
        return ResponseEntity.ok(taskService.findTaskByIdUser(idUser));
    }

    @PutMapping("/idUser/{idUser}")
    public ResponseEntity<?> updateTaskByIdUser(@PathVariable Integer idUser, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(task, idUser));
    }

}
