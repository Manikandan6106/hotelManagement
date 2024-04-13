//GuestRepository
package com.example.hotelmanagement.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.hotelmanagement.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    // JPQL query to find guests by name
    //

    @Query("SELECT g FROM Guest g WHERE g.name = :name ORDER BY g.name ASC")
    Page<Guest> findByName(String name, Pageable pageable);

    // JPQL query to find guests by email
    @Query("SELECT g FROM Guest g WHERE g.email = :email ORDER BY g.email ASC")
    Page<Guest> findByEmail(String email, Pageable pageable);
}
