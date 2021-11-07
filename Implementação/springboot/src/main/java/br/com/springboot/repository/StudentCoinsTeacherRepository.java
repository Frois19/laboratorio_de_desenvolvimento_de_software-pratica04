package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.StudentCoinsTeacher;

public interface StudentCoinsTeacherRepository extends JpaRepository<StudentCoinsTeacher, Long> {

}
