package com.users_ms.integration;

import com.users_ms.dto.TaskRequestDto;
import com.users_ms.dto.TaskResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-tasks")
public interface ConectionToMSTask {

    @PostMapping("/tasks")
    TaskResponseDto saveTask(@RequestBody TaskRequestDto responseDto);

    @GetMapping("/tasks/allByIdUser/{idUser}")
    List<TaskResponseDto> getAllTasksByIdUser(@PathVariable ("idUser") String idUser);

    @GetMapping("/tasks/{idTask}/idUser/{idUser}")
    Optional<TaskResponseDto> getTaskByIdTaskAndIdUser(@PathVariable ("idUser") String idUser, @PathVariable("idTask") Integer idTask);

    @PutMapping("/tasks/{idTask}/idUser/{idUser}")
    TaskResponseDto updateTaskByIdTaskAndIdUser(@PathVariable ("idUser") String idUser, @PathVariable ("idTask") Integer idTask , @RequestBody TaskRequestDto task);

    @DeleteMapping("/tasks{idTask}/idUser/{idUser}")
    Boolean deleteTaskByTaskAndIdUser(@PathVariable ("idUser") String idUser, @PathVariable ("idTask") Integer idTask);

}
