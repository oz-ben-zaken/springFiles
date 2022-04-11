package com.Bookstore.demo.Repository;

import com.Bookstore.demo.DTO.Book;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepo extends CrudRepository<Book,Integer> {
    //get all
    //get id
    //update
    //delete
}
