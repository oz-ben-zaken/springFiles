package com.Bookstore.demo.Service;

import com.Bookstore.demo.DTO.Book;
import com.Bookstore.demo.Repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BooksRepo booksRepo;

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        booksRepo.findAll().forEach(books::add);
        return books;
    }

    public Book getBook(int id){
        return booksRepo.findById(id).orElse(null);
    }

    public void addBook(Book book){
        booksRepo.save(book);
    }

    public void deleteBook(Book book){
        booksRepo.delete(book);
    }
}
