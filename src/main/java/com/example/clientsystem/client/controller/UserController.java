package com.example.clientsystem.client.controller;

import com.example.clientsystem.client.dto.ExternalPostDTO;
import com.example.clientsystem.client.dto.UserRequestDTO;
import com.example.clientsystem.client.model.UserInformation;
import com.example.clientsystem.client.service.UserInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Endpoints for managing user information")
public class UserController {

    private final UserInformationService userService;

    @PostMapping
    @Operation(summary = "Create a new user", description = "Saves user information to the MSSQL database")
    public ResponseEntity<UserInformation> createUser(@Valid @RequestBody UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping
    @Operation(summary = "Get all users with pagination")
    public ResponseEntity<Page<UserInformation>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(userService.getAllUsersPaged(PageRequest.of(page, size)));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete a user by email")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @GetMapping("/{id}/external-posts")
    @Operation(summary = "Fetch external data for a specific user",
            description = "Calls a 3rd party API to get posts related to this user ID")
    public ResponseEntity<List<ExternalPostDTO>> getExternalData(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getExternalPostsByUser(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update user information",
            description = "Updates only the fields provided. Also updates updatedAt and updatedBy.")
    public ResponseEntity<UserInformation> patchUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO userDTO) {

        return ResponseEntity.ok(userService.patchUser(id, userDTO));
    }
}
