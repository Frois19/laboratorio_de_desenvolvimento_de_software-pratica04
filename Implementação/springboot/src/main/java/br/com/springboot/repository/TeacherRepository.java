package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
}
