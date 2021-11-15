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

import br.com.springboot.model.Teacher;
import br.com.springboot.model.TeacherUniveristy;
import br.com.springboot.model.University;
import br.com.springboot.repository.TeacherRepository;
import br.com.springboot.repository.TeacherUniversityRepository;
import br.com.springboot.repository.UniversityRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private TeacherUniversityRepository teacherUniversityRepository;

    @PostMapping()
    public ResponseEntity<Teacher> postTeacher(@RequestBody Teacher teacher) {
        if (teacher.getLogin() != null) {
            Teacher response = this.teacherRepository.save(teacher);

            Optional<University> requestFind = this.universityRepository.findById(teacher.getId_univeristy());

            if (requestFind.isPresent()) {
                TeacherUniveristy teacherUniveristy = new TeacherUniveristy();

                teacherUniveristy.setId_teacher(response.getId());
                teacherUniveristy.setId_univeristy(teacher.getId_univeristy());

                this.teacherUniversityRepository.save(teacherUniveristy);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<Teacher>> login(@RequestBody Teacher teacher) {

        List<Teacher> teachers = new ArrayList<>();
        teachers = this.teacherRepository.findAll();

        Optional<Teacher> matchingObject = teachers.stream()
                .filter(p -> p.getLogin().equals(teacher.getLogin()) && p.getPassword().equals(teacher.getPassword()))
                .findFirst();

        if (matchingObject.isPresent()) {
            return new ResponseEntity<Optional<Teacher>>(matchingObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable("id") Long id) {

        Optional<Teacher> requestFind = this.teacherRepository.findById(id);
        if (requestFind.isPresent()) {
            return requestFind.get();
        }
        return null;
    }

    @GetMapping()
    public List<Teacher> listTeacher() {
        return this.teacherRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteTeacher(@PathVariable("id") Long id) {

        Optional<Teacher> requestFind = this.teacherRepository.findById(id);

        if (requestFind.isPresent()) {
            this.teacherRepository.deleteById(id);
        }
    }
}
