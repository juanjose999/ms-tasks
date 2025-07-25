package com.users_ms.service;

import com.users_ms.dto.MyUserMapper;
import com.users_ms.dto.MyUserResponseDto;
import com.users_ms.exception.MyUserException;
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

    public MyUserResponseDto saveUser(MyUser myUser){
        return MyUserMapper.entityToResponseDto( myUserRepository.save(myUser));
    }

    public Collection<?> saveTask(TaskRequestDto taskRequestDto) throws MyUserException {
        Optional<MyUser> findUser = myUserRepository.findById(taskRequestDto.id_user());
        TaskRequestDto taskRequestBody = new TaskRequestDto(taskRequestDto.title(),taskRequestDto.content(),findUser.get().getId());
        TaskResponseDto saveInTaskService = conectionToMSTask.saveTask(taskRequestBody);
        if(saveInTaskService == null) throw new MyUserException("No se pudo guardar la tarea.");
        return Collections.singleton(Map.of("task", saveInTaskService));
    }

    public Optional<MyUser> findUserById(String id) throws MyUserException {
        return myUserRepository.findById(id);
    }

    public Collection<?> findAllTasksByEmailUser(String emailUser) throws MyUserException {
        final Optional<MyUser> findUser = myUserRepository.findByEmail(emailUser);
        final List<TaskResponseDto> findTasksInExternalService = conectionToMSTask.getAllTasksByIdUser(findUser.get().getId());
        return Collections.singleton(Map.of("User", findUser.get().getUsername(), "tasks", findTasksInExternalService.isEmpty() ? "No has creado tareas." : findTasksInExternalService));
    }

    public TaskResponseDto findTaskByIdTaskAndEmailUser(String emailUser, Integer idTask) throws MyUserException {
        final MyUser findUser = myUserRepository.findById(emailUser).get();
        return conectionToMSTask.getTaskByIdTaskAndIdUser(findUser.getId(), idTask).orElseThrow(() -> new RuntimeException("No encontro la tarea."));
    }


    public MyUserResponseDto findUserByEmail(String email) throws MyUserException {
        Optional<MyUser> findUser =  myUserRepository.findByEmail(email);
        if(findUser.isPresent()) return MyUserMapper.entityToResponseDto(findUser.get());
        throw new MyUserException("No se pudo encontrar el usuario.");
    }

    public MyUserResponseDto updateUser(MyUser userNewData, String email) throws MyUserException {
        return MyUserMapper.entityToResponseDto(myUserRepository.updateUserByEmail(userNewData, email));
    }

    public TaskResponseDto updateTaskByIdAndEmailUser(Integer idTask,TaskRequestDto taskRequestDto, String emailUser) throws MyUserException {
        Optional<MyUser> findUser = myUserRepository.findByEmail(emailUser);
        TaskRequestDto request = new TaskRequestDto(taskRequestDto.title(),taskRequestDto.content(),findUser.get().getId());
        return conectionToMSTask.updateTaskByIdTaskAndIdUser(findUser.get().getId(), idTask, taskRequestDto);
    }

    public Boolean deleteUserByEmail(String email) throws MyUserException {
        return myUserRepository.deleteUserByEmail(email);
    }

    public Boolean deleteTaskByIdTaskAndEmailUser(Integer idTask, String emailUser) throws MyUserException {
        Optional<MyUser> findUser = myUserRepository.findByEmail(emailUser);
        return conectionToMSTask.deleteTaskByTaskAndIdUser(findUser.get().getId(), idTask);
    }

}
