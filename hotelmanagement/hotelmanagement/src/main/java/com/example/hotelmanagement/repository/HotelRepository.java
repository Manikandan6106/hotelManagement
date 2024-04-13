package com.example.hotelmanagement.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hotelmanagement.model.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // JPQL query to find hotels by city
    @Query("SELECT h FROM Hotel h WHERE h.city = :city")
    List<Hotel> findByCity(String city);
}
