package com.users_ms.repository;


import com.users_ms.entity.MyUser;
import com.users_ms.exception.MyUserException;

import java.util.Optional;


public interface IMyUserRepository  {
    MyUser save(MyUser myUser);
    Optional<MyUser> findByEmail(String email) throws MyUserException;
    Optional<MyUser> findById(String id) throws MyUserException;
    MyUser updateUserByEmail(MyUser myUser, String email) throws MyUserException;
    Boolean deleteUserByEmail(String email) throws MyUserException;
}
