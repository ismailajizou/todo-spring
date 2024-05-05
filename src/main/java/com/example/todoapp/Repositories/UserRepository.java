package com.example.todoapp.Repositories;

import com.example.todoapp.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
