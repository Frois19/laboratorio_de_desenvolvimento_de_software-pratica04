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

import br.com.springboot.model.Company;
import br.com.springboot.repository.CompanyRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping()
    public Company postCompany(@RequestBody Company company) {
        return this.companyRepository.save(company);
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable("id") Long id) {

        Optional<Company> requestFind = this.companyRepository.findById(id);
        if (requestFind.isPresent()) {
            return requestFind.get();
        }
        return null;
    }

    @GetMapping()
    public List<Company> listCompany() {
        return this.companyRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteCompany(@PathVariable("id") Long id) {

        Optional<Company> requestFind = this.companyRepository.findById(id);

        if (requestFind.isPresent()) {
            this.companyRepository.deleteById(id);
        }
    }
}
