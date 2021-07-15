package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TearsheetRoe;

@Repository
public interface TearsheetRoeRepository extends JpaRepository<TearsheetRoe, String>{

}
