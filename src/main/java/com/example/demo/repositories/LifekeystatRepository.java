package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Lifekeystat;

@Repository
public interface LifekeystatRepository extends JpaRepository<Lifekeystat, Integer>{

}
