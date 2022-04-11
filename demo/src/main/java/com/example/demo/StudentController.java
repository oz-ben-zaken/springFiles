package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("student")
public class StudentController {

    private static List<StudentDTO> stds = new ArrayList<>();
    private static int ID = 0;

    private static int getID() {
        return ++ID;
    }

    static {
        stds.add(new StudentDTO("oz", "ben zaken", getID(), 2, 91.4));
        stds.add(new StudentDTO("sharon", "tasa", getID(), 1, 93.4));
        stds.add(new StudentDTO("shon", "shavit", getID(), 3, 86.4));
    }

    @RequestMapping("/{id}")
    public StudentDTO getStudent(@PathVariable int id) {
        return stds.stream().filter(s -> s.getId() == id).findFirst().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/")
    public List<StudentDTO> getAllStudents() {
        return stds;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students")
    public void addMovie(@RequestBody StudentDTO student) {
        student.setId(getID());
        stds.add(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@RequestBody StudentDTO student, @PathVariable int id) {
        stds.remove(getStudent(id));
        stds.add(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        stds.remove(getStudent(id));
    }
}
