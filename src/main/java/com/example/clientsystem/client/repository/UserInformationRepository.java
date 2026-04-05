package com.example.clientsystem.client.repository;



import com.example.clientsystem.client.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

    // Spring Data JPA magic: It generates the SQL based on the method name!
    void deleteByEmailAddress(String email);

    // Also helpful to check if they exist first
    boolean existsByEmailAddress(String email);
}