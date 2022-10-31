package com.autodb_api.repository;

import com.autodb_api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);
}