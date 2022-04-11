package com.Bookstore.demo.mongoDB.DTO;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document("footballTeams") //change collection name
public class FootballTeam {
    @Id
    private int id;
    @Indexed(unique = true)     //set unique
    private String name;
    private String league;
    private long value;
    @Field("goals")     //change column name
    private int numOfGoals;
}
