package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TearsheetEp;

@Repository
public interface TearsheetEpRepository extends JpaRepository<TearsheetEp, String>{

}
