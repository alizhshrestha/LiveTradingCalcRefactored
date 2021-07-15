package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cbkeystat;

@Repository
public interface CbkeystatRepository extends JpaRepository<Cbkeystat, Integer>{

}
