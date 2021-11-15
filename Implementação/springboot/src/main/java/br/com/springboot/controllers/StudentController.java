package br.com.springboot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Student;
import br.com.springboot.model.StudentUniversity;
import br.com.springboot.model.University;
import br.com.springboot.repository.StudentRepository;
import br.com.springboot.repository.StudentUniversityRepository;
import br.com.springboot.repository.UniversityRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private StudentUniversityRepository studentUniversityRepository;

    @PostMapping()
    public ResponseEntity<Student> postStudent(@RequestBody Student student) {
        Student response = this.studentRepository.save(student);

        Optional<University> requestFind = this.universityRepository.findById(student.getId_univeristy());
        
        if (requestFind.isPresent()) {

            StudentUniversity studentUniveristy = new StudentUniversity();

            studentUniveristy.setId_student(response.getId());
            studentUniveristy.setId_univeristy(student.getId_univeristy());

            this.studentUniversityRepository.save(studentUniveristy);
            return new ResponseEntity<>(HttpStatus.OK);
        
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login") 
    public ResponseEntity<Optional<Student>> login(@RequestBody Student student) {

        List<Student> students =  new ArrayList<>();
        students = this.studentRepository.findAll();

        Optional<Student> matchingObject = students.stream().
        filter(p -> p.getLogin().equals(student.getLogin()) && p.getPassword().equals(student.getPassword())).
        findFirst();
        
        if(matchingObject.isPresent()){
            return new ResponseEntity<Optional<Student>>(matchingObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Long id) {

        Optional<Student> requestFind = this.studentRepository.findById(id);
        if (requestFind.isPresent()) {
            return requestFind.get();
        }
        return null;
    }

    @GetMapping()
    public List<Student> listStudent() {
        return this.studentRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable("id") Long id) {

        Optional<Student> requestFind = this.studentRepository.findById(id);

        if (requestFind.isPresent()) {
            this.studentRepository.deleteById(id);
        }
    }
}
