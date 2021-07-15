package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TearsheetPbv;

@Repository
public interface TearsheetPbvRepository extends JpaRepository<TearsheetPbv, String>{

}
