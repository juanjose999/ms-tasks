package com.users_ms.repository;

import com.users_ms.entity.MyUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MyUserRepository{

    private final IMyUserRepository myUserRepository;

    public MyUserRepository(IMyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    MyUser saveUser(MyUser myUser) {
        return myUserRepository.save(myUser);
    }

    Optional<MyUser> findByEmail(String email){
        return myUserRepository.findByEmail(email);
    }

    MyUser updateUserByEmail(MyUser myUser, String email) {
        Optional<MyUser> findUser = findByEmail(email);
        if(findUser.isEmpty()) throw new IllegalArgumentException("No se encontro el usuario");
        if(findUser.get().getUsername()!=null) myUser.setUsername(findUser.get().getUsername());
        if(findUser.get().getPassword()!=null) myUser.setPassword(findUser.get().getPassword());
        return myUserRepository.save(myUser);
    }

    Boolean deleteUserByEmail(String email) {
        Optional<MyUser> findUser = findByEmail(email);
        if(findUser.isEmpty()) throw new IllegalArgumentException("No se encontro el usuario");
        myUserRepository.deleteById(findUser.get().getId());
        return true;
    }

}
