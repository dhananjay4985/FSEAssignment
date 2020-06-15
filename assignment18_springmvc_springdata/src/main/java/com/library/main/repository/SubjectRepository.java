package com.library.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.main.model.Subject;

@Repository("subjectRepository")
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
