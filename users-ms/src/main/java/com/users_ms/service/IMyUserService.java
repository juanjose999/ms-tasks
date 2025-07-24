package com.users_ms.service;

import com.users_ms.dto.TaskRequestDto;
import com.users_ms.dto.TaskResponseDto;
import com.users_ms.entity.MyUser;

import java.util.Collection;

public interface IMyUserService {
    MyUser saveUser(MyUser myUser);
    Collection<?> saveTask(TaskRequestDto taskRequestDto);
    Collection<?> findAllTasksByEmailUser(String emailUser);
    TaskResponseDto findTaskByIdTaskAndEmailUser(String emailUser, Integer idTask);
    MyUser findUserByEmail(String email);
    MyUser updateUser(MyUser userNewData, String email);
    TaskResponseDto updateTaskByIdAndEmailUser(Integer idTask,TaskRequestDto taskRequestDto, String emailUser);
    Boolean deleteUserByEmail(String email);
    Boolean deleteTaskByIdTaskAndEmailUser(Integer idTask, String emailUser);
}
