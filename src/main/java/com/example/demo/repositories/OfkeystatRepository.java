package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ofkeystat;

@Repository
public interface OfkeystatRepository extends JpaRepository<Ofkeystat, Integer>{

}
