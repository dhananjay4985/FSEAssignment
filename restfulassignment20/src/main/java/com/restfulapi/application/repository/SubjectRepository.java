package com.restfulapi.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restfulapi.application.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
