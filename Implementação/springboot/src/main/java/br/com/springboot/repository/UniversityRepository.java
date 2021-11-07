package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.University;

public interface UniversityRepository  extends JpaRepository<University, Long>{
    
}
