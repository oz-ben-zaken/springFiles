package com.Bookstore.demo.mongoDB.repository;

import com.Bookstore.demo.mongoDB.DTO.FootballTeam;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.repository.CrudRepository;
//CrudRepository is for sql pojos

public interface FootballTeamRepo extends MongoRepository<FootballTeam,Integer> {
}
