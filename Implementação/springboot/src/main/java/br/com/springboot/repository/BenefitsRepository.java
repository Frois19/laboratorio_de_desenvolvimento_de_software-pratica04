package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Benefits;

public interface BenefitsRepository extends JpaRepository<Benefits, Long> { 
    
}
