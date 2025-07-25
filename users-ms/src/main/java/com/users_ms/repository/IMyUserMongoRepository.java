package com.users_ms.repository;

import com.users_ms.entity.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IMyUserMongoRepository extends MongoRepository<MyUser, String> {
    Optional<MyUser> findByEmail(String email);
}
