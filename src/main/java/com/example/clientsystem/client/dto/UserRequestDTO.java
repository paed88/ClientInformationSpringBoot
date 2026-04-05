package com.example.clientsystem.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    private String title;

    @NotBlank(message = "Full name is required")
    private String fullName;
    private String gender;

    @NotBlank(message = "ID type is required")
    private String idType;

    @NotBlank(message = "ID number is required")
    private String idNumber;

    private String nationality;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String emailAddress;

    @NotBlank(message = "Home address is required")
    private String homeAddress;

    @NotBlank(message = "Postcode number is required")
    private String postcode;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @Size(min = 12, max = 13, message = "Mobile number should be between 10-13 digits")
    private String mobileNumber;

}
