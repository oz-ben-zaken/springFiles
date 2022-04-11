package com.Bookstore.demo.mongoDB.controller;

import com.Bookstore.demo.mongoDB.DTO.FootballTeam;
import com.Bookstore.demo.mongoDB.service.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/footballteams")
public class FootballTeamController {

    @Autowired
    FootballTeamService footballTeamService;

    @GetMapping("/{id}")
    public FootballTeam get(int id){
        return footballTeamService.get(id);
    }

    @GetMapping
    public List<FootballTeam> getAll(){
        return footballTeamService.getAll();
    }

    @PostMapping
    public void add(@RequestBody FootballTeam team){
        footballTeamService.add(team);
    }

    @PutMapping
    public void update(@RequestBody FootballTeam team){
        footballTeamService.update(team);
    }

    @DeleteMapping("/{id}")
    public void delete(int id){
        footballTeamService.delete(id);
    }
}
