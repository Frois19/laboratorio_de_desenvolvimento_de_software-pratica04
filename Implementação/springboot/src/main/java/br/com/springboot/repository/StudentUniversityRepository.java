package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.StudentUniversity;

public interface StudentUniversityRepository extends JpaRepository<StudentUniversity, Long>{
    
}
