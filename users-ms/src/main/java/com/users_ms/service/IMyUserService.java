package com.users_ms.service;

import com.users_ms.dto.MyUserResponseDto;
import com.users_ms.dto.TaskRequestDto;
import com.users_ms.dto.TaskResponseDto;
import com.users_ms.entity.MyUser;
import com.users_ms.exception.MyUserException;

import java.util.Collection;

public interface IMyUserService {
    MyUserResponseDto saveUser(MyUser myUser);
    Collection<?> saveTask(TaskRequestDto taskRequestDto) throws MyUserException;
    Collection<?> findAllTasksByEmailUser(String emailUser) throws MyUserException;
    TaskResponseDto findTaskByIdTaskAndEmailUser(String emailUser, Integer idTask) throws MyUserException;
    MyUserResponseDto findUserByEmail(String email) throws MyUserException;
    MyUserResponseDto updateUser(MyUser userNewData, String email) throws MyUserException;
    TaskResponseDto updateTaskByIdAndEmailUser(Integer idTask,TaskRequestDto taskRequestDto, String emailUser) throws MyUserException;
    Boolean deleteUserByEmail(String email) throws MyUserException;
    Boolean deleteTaskByIdTaskAndEmailUser(Integer idTask, String emailUser) throws MyUserException;
}
