package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Financekeystat;

@Repository
public interface FinancekeystatRepository extends JpaRepository<Financekeystat, Integer>{

}
