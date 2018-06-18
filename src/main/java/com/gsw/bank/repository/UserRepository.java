package com.gsw.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.gsw.bank.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
