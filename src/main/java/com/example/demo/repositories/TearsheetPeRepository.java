package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TearsheetPe;

@Repository
public interface TearsheetPeRepository extends JpaRepository<TearsheetPe, String>{

}
