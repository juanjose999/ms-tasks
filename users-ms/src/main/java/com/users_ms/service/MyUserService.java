package com.users_ms.service;

import com.users_ms.integration.ConectionToMSTask;
import com.users_ms.dto.TaskRequestDto;
import com.users_ms.dto.TaskResponseDto;
import com.users_ms.entity.MyUser;
import com.users_ms.repository.IMyUserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserService implements IMyUserService {

    private final IMyUserRepository myUserRepository;
    private final ConectionToMSTask conectionToMSTask;

    public MyUserService(IMyUserRepository myUserRepository, ConectionToMSTask conectionToMSTask) {
        this.myUserRepository = myUserRepository;
        this.conectionToMSTask = conectionToMSTask;
    }

    public MyUser saveUser(MyUser myUser){
        return myUserRepository.saveUser(myUser);
    }

    public Collection<?> saveTask(TaskRequestDto taskRequestDto){
        MyUser findUser = findUserByEmail(taskRequestDto.emailUser());
        TaskResponseDto taskRequestBody = new TaskResponseDto(taskRequestDto.title(),taskRequestDto.content(),findUser.getId());
        TaskResponseDto saveInTaskService = conectionToMSTask.saveTask(taskRequestBody);
        if(saveInTaskService == null) throw new RuntimeException("No se pudo guardar la tarea.");
        return Collections.singleton(Map.of("task", saveInTaskService));
    }

    public Collection<?> findAllTasksByEmailUser(String emailUser){
        final MyUser findUser = findUserByEmail(emailUser);
        final List<TaskResponseDto> findTasksInExternalService = conectionToMSTask.getAllTasksByIdUser(findUser.getId());
        return Collections.singleton(Map.of("User", findUser.getUsername(), "tasks", findTasksInExternalService.isEmpty() ? "No has creado tareas." : findTasksInExternalService));
    }

    public TaskResponseDto findTaskByIdTaskAndEmailUser(String emailUser, Integer idTask){
        final MyUser findUser = findUserByEmail(emailUser);
        return conectionToMSTask.getTaskByIdTaskAndIdUser(findUser.getId(), idTask).orElseThrow(() -> new RuntimeException("No encontro la tarea."));
    }


    public MyUser findUserByEmail(String email){
        Optional<MyUser> findUser =  myUserRepository.findByEmail(email);
        if(findUser.isPresent()) return findUser.get();
        throw new RuntimeException("No se pudo encontrar el usuario.");
    }

    public MyUser updateUser(MyUser userNewData, String email){
        return myUserRepository.updateUserByEmail(userNewData, email);
    }

    public TaskResponseDto updateTaskByIdAndEmailUser(Integer idTask,TaskRequestDto taskRequestDto, String emailUser){
        MyUser findUser = findUserByEmail(emailUser);
        TaskRequestDto request = new TaskRequestDto(taskRequestDto.title(),taskRequestDto.content(),findUser.getId());
        TaskResponseDto response = conectionToMSTask.updateTaskByIdTaskAndIdUser(findUser.getId(), idTask, taskRequestDto);
        return response;
    }

    public Boolean deleteUserByEmail(String email){
        return myUserRepository.deleteUserByEmail(email);
    }

    public Boolean deleteTaskByIdTaskAndEmailUser(Integer idTask, String emailUser){
        MyUser findUser = findUserByEmail(emailUser);
        return conectionToMSTask.deleteTaskByTaskAndIdUser(findUser.getId(), idTask);
    }

}
