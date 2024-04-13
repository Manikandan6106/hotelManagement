package com.example.hotelmanagement.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import com.example.hotelmanagement.model.Guest;
import com.example.hotelmanagement.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;


    @GetMapping("/guests/byName")
    public Page<Guest> getGuestsByName(@RequestParam String name, Pageable pageable) {
        return guestService.getGuestsByName(name, pageable);
    }

    @GetMapping("/guests/byEmail")
    public Page<Guest> getGuestsByEmail(@RequestParam String email, Pageable pageable) {
        return guestService.getGuestsByEmail(email, pageable);
    }


    // Create a new guest
    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest savedGuest = guestService.createGuest(guest);
        return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
    }

    // Get all guests with pagination and sorting
    @GetMapping
    public ResponseEntity<Page<Guest>> getAllGuests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, direction, sortField);
        Page<Guest> guests = guestService.getAllGuests(pageable);
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }


    // Get guest by ID
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        Guest guest = guestService.getGuestById(id);
        if (guest != null) {
            return new ResponseEntity<>(guest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update guest
    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuest(id, guest);
        if (updatedGuest != null) {
            return new ResponseEntity<>(updatedGuest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete guest
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
