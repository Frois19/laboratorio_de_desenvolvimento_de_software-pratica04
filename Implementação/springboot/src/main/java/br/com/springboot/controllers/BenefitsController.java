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

import br.com.springboot.model.Benefits;
import br.com.springboot.repository.BenefitsRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/benefits")
public class BenefitsController {
    @Autowired
    private BenefitsRepository benefitsRepository;

    @PostMapping()
    public Benefits postBenefits(@RequestBody Benefits Benefits) {
        return this.benefitsRepository.save(Benefits);
    }

    @GetMapping("/{id}")
    public Benefits getBenefits(@PathVariable("id") Long id) {

        Optional<Benefits> requestFind = this.benefitsRepository.findById(id);
        if (requestFind.isPresent()) {
            return requestFind.get();
        }
        return null;
    }

    @GetMapping()
    public List<Benefits> listBenefits() {
        return this.benefitsRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteBenefits(@PathVariable("id") Long id) {

        Optional<Benefits> requestFind = this.benefitsRepository.findById(id);

        if (requestFind.isPresent()) {
            this.benefitsRepository.deleteById(id);
        }
    }
}
