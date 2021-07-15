package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Latestprice;

@Repository
public interface LatestpriceRepository extends JpaRepository<Latestprice, String>{

}
