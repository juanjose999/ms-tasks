package com.users_ms;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ms-tasks")
public interface ConectionToMSTask {

    @GetMapping("/tasks/allByIdUser/{idUser}")
    List<TaskResponseDto> getAllTasksByIdUser(@PathVariable ("idUser") String idUser);

    @GetMapping("/tasks/{idTask}/idUser/{idUser}")
    TaskResponseDto getTaskByIdAndIdUser(@PathVariable ("idUser") String idUser, @PathVariable("idTask") Integer idTask);

    @PostMapping("/tasks")
    TaskResponseDto createTask(@RequestBody TaskResponseDto responseDto);
}
