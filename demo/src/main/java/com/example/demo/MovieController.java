package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    public static List<Movie> movies = new ArrayList<Movie>();
    public static int ID = 4;

    private int getID(){
        return ++ID;
    }

    static{
        movies.add(new Movie(1,"first",120,59));
        movies.add(new Movie(2,"second",110,45));
        movies.add(new Movie(3,"third",100,80));
        movies.add(new Movie(4,"fourth",95,30));
    }

    @RequestMapping("/movies/{id}")
    public Movie getMovie(@PathVariable String id)
    {
        var _id = Integer.parseInt(id);
        for(var movie :movies)
            if (movie.id == _id)
                return movie;
        return new Movie();
    }
    @GetMapping("/movies")
    public List<Movie> getAllMovies()
    {
        return movies;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/movie")
    public Movie addMovie(@RequestBody Movie movie){
        movie.id=getID();
        movies.add(movie);
        return movie;
    }

    @PutMapping("/movies/{id}")
    public boolean updateMovie(@RequestBody Movie movie,@PathVariable String id)
    {
        var _id = Integer.parseInt(id);
        movie.id = _id;
        for(var m :movies)
            if (m.id == _id) {
                var index = movies.indexOf(m);
                movies.remove(index);
                movies.add(index,movie);
                return true;
            }
        return false;
    }

    @DeleteMapping("/movies/{id}")
    public String deleteMovie(@PathVariable String id)
    {
        var _id = Integer.parseInt(id);
        for(var m :movies)
            if (m.id == _id) {
                var index = movies.indexOf(m);
                movies.remove(index);
                return "done";
            }
        return "som ting went wong";
    }

}
