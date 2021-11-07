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
    public Teacher postTeacher(@RequestBody Teacher teacher) {

        this.teacherRepository.save(teacher);
        Optional<University> requestFind = this.universityRepository.findById(teacher.getUniversity_id());

        if (requestFind.isPresent()) {

            TeacherUniveristy teacherUniveristy = new TeacherUniveristy();
            teacherUniveristy.setTeacher_id(teacher.getId());
            teacherUniveristy.setUniversity_id(teacher.getUniversity_id());
            this.teacherUniversityRepository.save(teacherUniveristy);
        
        }
        return null;
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
