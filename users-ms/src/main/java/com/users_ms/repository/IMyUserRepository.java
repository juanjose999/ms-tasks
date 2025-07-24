package com.users_ms.repository;


import com.users_ms.entity.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMyUserRepository extends MongoRepository<MyUser, String> {
    MyUser saveUser(MyUser myUser);
    Optional<MyUser> findByEmail(String email);
    MyUser updateUserByEmail(MyUser myUser, String email);
    Boolean deleteUserByEmail(String email);
}
