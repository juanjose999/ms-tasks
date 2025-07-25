package com.users_ms.controller;

import com.users_ms.dto.MyUserResponseDto;
import com.users_ms.exception.MyUserException;
import com.users_ms.service.MyUserService;
import com.users_ms.dto.TaskRequestDto;
import com.users_ms.entity.MyUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MyUserController {

    private final MyUserService myUserService;

    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> saveTask(@RequestBody TaskRequestDto taskRequestDto) throws MyUserException {
        return ResponseEntity.ok(myUserService.saveTask(taskRequestDto));
    }

    @PostMapping
    public ResponseEntity<MyUserResponseDto> saveUser(@RequestBody MyUser myUser) {
        return ResponseEntity.ok(myUserService.saveUser(myUser));
    }

    @GetMapping("/all-tasks/{emailUser}")
    public ResponseEntity<?> getAllTasksByEmailUser(@PathVariable String emailUser) throws MyUserException {
        return ResponseEntity.ok(myUserService.findAllTasksByEmailUser(emailUser));
    }

    @GetMapping("/{emailUser}/task-id/{idTask}")
    public ResponseEntity<?> getTaskByIdAndIdUser(@PathVariable String emailUser, @PathVariable Integer idTask) throws MyUserException {
        return ResponseEntity.ok(myUserService.findTaskByIdTaskAndEmailUser(emailUser, idTask));
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) throws MyUserException {
        return ResponseEntity.ok(myUserService.findUserByEmail(email));
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<?> updateUser(@RequestBody MyUser myUser, @PathVariable String email) throws MyUserException {
        return ResponseEntity.ok(myUserService.updateUser(myUser, email));
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) throws MyUserException {
        return myUserService.deleteUserByEmail(email) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
