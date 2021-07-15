package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StockData;

@Repository
public interface StockDataRepository extends JpaRepository<StockData, Integer>{

}
