package com.demo.DrFlight.JPA.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // Data Transfer Object
@Table(name="contactus")
public class Contactus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int messageId;
    String senderNaMe;
    String contact;
}
