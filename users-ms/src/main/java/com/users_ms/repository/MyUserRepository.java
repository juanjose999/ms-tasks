package com.users_ms.repository;

import com.users_ms.entity.MyUser;
import com.users_ms.exception.MyUserException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MyUserRepository implements IMyUserRepository{

    private final IMyUserMongoRepository myUserRepository;

    public MyUserRepository(IMyUserMongoRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    public MyUser save(MyUser myUser){
        return myUserRepository.save(myUser);
    }
    public Optional<MyUser> findByEmail(String email) throws MyUserException {
        return Optional.ofNullable(myUserRepository.findByEmail(email).orElseThrow(() -> new MyUserException("No se encontro al usuario con el email: " + email)));
    }
    public Optional<MyUser> findById(String id) throws MyUserException {
        return Optional.of(myUserRepository.findById(id)).orElseThrow(() -> new MyUserException("No se encontro al usuario."));
    }
    public MyUser updateUserByEmail(MyUser myUser, String email) throws MyUserException {
        Optional<MyUser> findUser = findByEmail(email);
        if(findUser.isEmpty()) throw new MyUserException("No se encontro el usuario con el email: " + email);
        findUser.get().setUsername(myUser.getUsername());
        findUser.get().setPassword(myUser.getPassword());
        return myUserRepository.save(myUser);
    }
    public Boolean deleteUserByEmail(String email) throws MyUserException {
        Optional<MyUser> findUser = findByEmail(email);
        if(findUser.isEmpty()) return false;
        myUserRepository.delete(findUser.get());
        return true;
    }
}
