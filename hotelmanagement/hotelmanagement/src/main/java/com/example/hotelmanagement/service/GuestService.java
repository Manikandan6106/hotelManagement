package com.example.hotelmanagement.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.hotelmanagement.model.Guest;
import com.example.hotelmanagement.repository.GuestRepository;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    // Create a new guest
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    // Get all guests with pagination and sorting
    public Page<Guest> getAllGuests(Pageable pageable) {
        return guestRepository.findAll(pageable);
    }

     // Get guests by name using JPQL
    public Page<Guest> getGuestsByName(String name, Pageable pageable) {
        return guestRepository.findByName(name, pageable);
    }

     // Get guests by email using JPQL
     public Page<Guest> getGuestsByEmail(String email, Pageable pageable) {
        return guestRepository.findByEmail(email, pageable);
    }

    // Get guest by ID
    public Guest getGuestById(Long id) {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        return optionalGuest.orElse(null);
    }

    // Update guest
    public Guest updateGuest(Long id, Guest newGuest) {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        if (optionalGuest.isPresent()) {
            Guest existingGuest = optionalGuest.get();
            existingGuest.setName(newGuest.getName());
            existingGuest.setEmail(newGuest.getEmail());
            existingGuest.setPhoneNumber(newGuest.getPhoneNumber());
            existingGuest.setCheckInDate(newGuest.getCheckInDate());
            existingGuest.setCheckOutDate(newGuest.getCheckOutDate());
            return guestRepository.save(existingGuest);
        } else {
            return null;
        }
    }

    // Delete guest
    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
}
