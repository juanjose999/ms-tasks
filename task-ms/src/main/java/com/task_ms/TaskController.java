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
    public ResponseEntity<?> getAllTasksByIdUser(@PathVariable String idUser) {
        return ResponseEntity.ok(taskService.findAllByIdUser(idUser));
    }

    @GetMapping("{idTask}/idUser/{idUser}")
    public ResponseEntity<?> getTaskByIdAndIdUser(@PathVariable String idUser, @PathVariable Integer idTask) {
        return ResponseEntity.ok(taskService.findTaskByIdAndIdUser(idUser, idTask));
    }

    @PutMapping("{idTask}/idUser/{idUser}")
    public ResponseEntity<?> updateTaskByIdUser(@PathVariable String idUser, @PathVariable Integer idTask ,@RequestBody Task task) {
        return ResponseEntity.ok(taskService.update(idTask, idUser, task));
    }

    @DeleteMapping("{idTask}/idUser/{idUser}")
    public ResponseEntity<?> deleteTaskByIdUser(@PathVariable Integer idTask, @PathVariable String idUser) {
        return taskService.deleteTaskByIdTaskAndIdUser(idTask,idUser) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
