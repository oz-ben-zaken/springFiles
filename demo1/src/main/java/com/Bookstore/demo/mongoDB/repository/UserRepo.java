package com.Bookstore.demo.mongoDB.repository;

import com.Bookstore.demo.mongoDB.DTO.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {
}
