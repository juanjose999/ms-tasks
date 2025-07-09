package com.users_ms;

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

    @PostMapping
    public ResponseEntity<MyUser> saveUser(@RequestBody MyUser myUser) {
        return ResponseEntity.ok(myUserService.save(myUser));
    }

    @PostMapping("/{emailUser}")
    public ResponseEntity<?> saveTask(@RequestBody TaskRequestDto taskRequestDto) {
        return ResponseEntity.ok(myUserService.saveTask(taskRequestDto));
    }

    @GetMapping("/{emailUser}")
    public ResponseEntity<?> getTasks(@PathVariable String emailUser) {
        return ResponseEntity.ok(myUserService.findAllTasksByUser(emailUser));
    }

    @GetMapping
    public ResponseEntity<List<MyUser>> getAllUsers() {
        return ResponseEntity.ok(myUserService.findAll());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<MyUser> findUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(myUserService.findByEmail(email));
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<MyUser> updateUser(@RequestBody MyUser myUser, @PathVariable String email) {
        return ResponseEntity.ok(myUserService.updateUser(myUser, email));
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) {
        return myUserService.deleteUserByEmail(email) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
