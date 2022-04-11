package com.Bookstore.demo.mongoDB.service;


import com.Bookstore.demo.mongoDB.DTO.FootballTeam;
import com.Bookstore.demo.mongoDB.repository.FootballTeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FootballTeamService {

    @Autowired
    FootballTeamRepo footballTeamRepo;

    public List<FootballTeam> getAll(){
        return new ArrayList<>(footballTeamRepo.findAll());
    }

    public FootballTeam get(int id){
        return footballTeamRepo.findById(id).orElse(null);
    }

    public void add(FootballTeam team){
        footballTeamRepo.save(team);
    }

    public void update(FootballTeam team){
        footballTeamRepo.save(team);
    }

    public void delete(int id){
        footballTeamRepo.deleteById(id);
    }
}
