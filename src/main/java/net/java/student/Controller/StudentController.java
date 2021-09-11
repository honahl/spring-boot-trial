package net.java.student.Controller;

import net.java.student.Entity.Student;
import net.java.student.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public List<Student> list(){
        return service.list();
    }

    @PostMapping("/students")
    public Student Crestudent(@Valid @RequestBody Student student){
        return service.save(student);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student>  getByID(@PathVariable(value = "id") Integer id){
        try{
            Student student = service.get(id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

}
