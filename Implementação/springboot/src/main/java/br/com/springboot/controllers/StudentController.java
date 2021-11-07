package br.com.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Student postStudent(@RequestBody Student student) {
        this.studentRepository.save(student);
        Optional<University> requestFind = this.universityRepository.findById(student.getUniveristy_id());
        
        if (requestFind.isPresent()) {

            StudentUniversity studentUniveristy = new StudentUniversity();
            studentUniveristy.setStudent_id(student.getId());
            studentUniveristy.setUniversity_id(student.getUniveristy_id());
            this.studentUniversityRepository.save(studentUniveristy);
        
        }
        return null;
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
