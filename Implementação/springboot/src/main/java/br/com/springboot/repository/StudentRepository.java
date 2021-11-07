package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
