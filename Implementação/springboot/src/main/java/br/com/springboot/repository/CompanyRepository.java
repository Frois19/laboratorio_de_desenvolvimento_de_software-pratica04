package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> { 
    
}
