package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.TeacherUniveristy;

public interface TeacherUniversityRepository extends JpaRepository<TeacherUniveristy, Long>{
    
}
