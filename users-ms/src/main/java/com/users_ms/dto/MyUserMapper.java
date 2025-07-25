package com.users_ms.dto;

import com.users_ms.entity.MyUser;

public class MyUserMapper {
    public static MyUserResponseDto entityToResponseDto(MyUser myUser) {
        return new MyUserResponseDto(myUser.getId(), myUser.getUsername(), myUser.getPassword(), myUser.getEmail(), String.valueOf(myUser.getCreated()), String.valueOf(myUser.getUpdated()));
    }
}
