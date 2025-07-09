package com.users_ms;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MyUserService {

    private final MyUserRepository myUserRepository;
    private final ConectionToMSTask conectionToMSTask;

    public MyUserService(MyUserRepository myUserRepository, ConectionToMSTask conectionToMSTask) {
        this.myUserRepository = myUserRepository;
        this.conectionToMSTask = conectionToMSTask;
    }

    public MyUser save(MyUser myUser){
        return myUserRepository.save(myUser);
    }

    public Collection<?> saveTask(TaskRequestDto taskRequestDto){
        MyUser findUser = findByEmail(taskRequestDto.emailUser());
        TaskResponseDto response = new TaskResponseDto(taskRequestDto.title(),taskRequestDto.content(),findUser.getId());
        TaskResponseDto resultCalloutToMSTask = conectionToMSTask.createTask(response);
        return Collections.singleton(Map.of("User", findUser, "task", resultCalloutToMSTask));
    }

    public Collection<?> findAllTasksByUser(String emailUser){
        MyUser findUser = findByEmail(emailUser);
        List<TaskResponseDto> result = conectionToMSTask.getAllTasksByIdUser(findUser.getId());
        return Collections.singleton(Map.of("User", findUser, "tasks", result));
    }

    public MyUser findByEmail(String email){
        return myUserRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<MyUser> findAll(){
        return myUserRepository.findAll();
    }

    public MyUser updateUser(MyUser userNewData, String email){
        MyUser findUser = findByEmail(email);
        findUser.setEmail(userNewData.getEmail());
        findUser.setUsername(userNewData.getUsername());
        findUser.setPassword(userNewData.getPassword());
        myUserRepository.save(findUser);
        return findUser;
    }

    public Boolean deleteUserByEmail(String email){
        MyUser findUser = findByEmail(email);
        if(findUser != null){
            myUserRepository.delete(findUser);
            return true;
        }
        return false;
    }

}
