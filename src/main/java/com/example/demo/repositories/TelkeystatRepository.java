package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Telkeystat;

@Repository
public interface TelkeystatRepository extends JpaRepository<Telkeystat, Integer>{

}
