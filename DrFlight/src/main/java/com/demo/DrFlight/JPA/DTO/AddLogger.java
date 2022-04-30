package com.demo.DrFlight.JPA.DTO;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document("footballTeams")
public class AddLogger {
    @Id
    private long id;
    private String time;
}
