package com.example.clientsystem.client.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_information")
@Data
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String title; // Mr, Ms, Dr, etc.

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(length = 10)
    private String gender;

    @Column(name = "id_type", length = 50)
    private String idType; // IC, Passport, etc.

    @Column(name = "id_number", length = 50, unique = true)
    private String idNumber;

    private String nationality;

    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAddress;

    @Column(name = "home_address", columnDefinition = "NVARCHAR(MAX)")
    private String homeAddress;

    @Column(length = 5)
    private String postcode;

    private String district;
    private String state;
    private String country;

    @Column(name = "mobile_number", length = 20)
    private String mobileNumber;

    // Audit fields (Best Practice for Technical Tests)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @Column(name = "updated_at", updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", updatable = false)
    private Long updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}