package com.Bookstore.demo.DTO;

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
@Table(name="Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length=500)
    private String name;
    @Column(name="dapim")
    private int pages;
    private double price;
    //
    // important notations
    // --auto increment
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // --unique
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // --change column name
    // @Column(name="dapim")
    // --dictate column length
    // @Column(length=500)
    // --change table name
    //@Table(name="Books")
    // --
    //
}
