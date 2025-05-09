package com.example.repository;
import java.util.Optional;


import com.example.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    List<Registration> findByUsername(String username);
    void deleteByUsernameAndCrn(String username, int crn);
    Optional<Registration> findByUsernameAndCrn(String username, int crn);

}


