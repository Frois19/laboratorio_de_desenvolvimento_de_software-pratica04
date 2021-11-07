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

import br.com.springboot.model.University;
import br.com.springboot.repository.UniversityRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    private UniversityRepository universityRepository;

    @PostMapping()
    public University postUniversity(@RequestBody University University) {
        return this.universityRepository.save(University);
    }

    @GetMapping("/{id}")
    public University getUniversity(@PathVariable("id") Long id) {

        Optional<University> requestFind = this.universityRepository.findById(id);
        if (requestFind.isPresent()) {
            return requestFind.get();
        }
        return null;
    }

    @GetMapping()
    public List<University> listUniversity() {
        return this.universityRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteUniversity(@PathVariable("id") Long id) {

        Optional<University> requestFind = this.universityRepository.findById(id);

        if (requestFind.isPresent()) {
            this.universityRepository.deleteById(id);
        }
    }
}
