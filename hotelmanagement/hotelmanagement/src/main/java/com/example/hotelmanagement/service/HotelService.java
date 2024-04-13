package com.example.hotelmanagement.service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.example.hotelmanagement.model.Hotel;
import com.example.hotelmanagement.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // Create a new hotel
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Get all hotels with pagination and sorting
public Page<Hotel> getAllHotels(Pageable pageable) {
    return hotelRepository.findAll(pageable);
}


    // Get hotels by city using JPQL
    public List<Hotel> getHotelsByCity(String city) {
        return hotelRepository.findByCity(city);
    }


    // Get hotel by ID
    public Hotel getHotelById(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        return optionalHotel.orElse(null);
    }

    // Update hotel
    public Hotel updateHotel(Long id, Hotel newHotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel existingHotel = optionalHotel.get();
            existingHotel.setName(newHotel.getName());
            existingHotel.setCity(newHotel.getCity());
            existingHotel.setContactInformation(newHotel.getContactInformation());
            return hotelRepository.save(existingHotel);
        } else {
            return null;
        }
    }

    // Delete hotel
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
