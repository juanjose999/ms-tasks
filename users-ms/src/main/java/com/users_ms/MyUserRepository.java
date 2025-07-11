package com.users_ms;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends MongoRepository<MyUser, String> {
    Optional<MyUser> findByEmail(String email);
}
