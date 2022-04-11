package com.Bookstore.demo.Controller;

import com.Bookstore.demo.DTO.Book;
import com.Bookstore.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/book")
    public List<Book> getAll(){
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public Book get(@PathVariable int id){
        var book = bookService.getBook(id);
        return (book!=null)? book:new Book();
    }

    @RequestMapping(method=RequestMethod.POST,value = "/book")
    public void addMovie( @RequestBody Book book){
        bookService.addBook(book);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable int id){
        var book = bookService.getBook(id);
        bookService.deleteBook(book);
    }
}
