package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Hotelkeystat;

@Repository
public interface HotelkeystatRepository extends JpaRepository<Hotelkeystat, Integer>{

}
