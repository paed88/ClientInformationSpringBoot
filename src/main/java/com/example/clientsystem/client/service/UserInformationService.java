package com.example.clientsystem.client.service;

import com.example.clientsystem.client.dto.ExternalPostDTO;
import com.example.clientsystem.client.dto.UserRequestDTO;
import com.example.clientsystem.client.model.UserInformation;
import com.example.clientsystem.client.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@DynamicUpdate
public class UserInformationService {

    private final UserInformationRepository repository;

    private final RestTemplate restTemplate;

    @Transactional
    public UserInformation createUser(UserRequestDTO dto) {
        UserInformation user = new UserInformation();

        user.setTitle(dto.getTitle());
        user.setFullName(dto.getFullName());
        user.setGender(dto.getGender());
        user.setIdType(dto.getIdType());
        user.setIdNumber(dto.getIdNumber());
        user.setNationality(dto.getNationality());
        user.setEmailAddress(dto.getEmailAddress());
        user.setHomeAddress(dto.getHomeAddress());
        user.setPostcode(dto.getPostcode());
        user.setDistrict(dto.getDistrict());
        user.setState(dto.getState());
        user.setCountry(dto.getCountry());
        user.setMobileNumber(dto.getMobileNumber());

        // Audit fields (handled by @PrePersist in the Entity, but we can set IDs here)
        user.setCreatedBy(1L); // Placeholder until we have JWT
        user.setUpdatedBy(1L);

        return repository.save(user);
    }

    public Page<UserInformation> getAllUsersPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        if (!repository.existsByEmailAddress(email)) {
            throw new RuntimeException("User with email " + email + " not found");
        }
        repository.deleteByEmailAddress(email);
    }

    public List<ExternalPostDTO> getExternalPostsByUser(Long userId) {
        String url = "https://jsonplaceholder.typicode.com/posts?userId=" + userId;

        // We use ParameterizedTypeReference to handle the List<ExternalPostDTO> correctly
        ResponseEntity<List<ExternalPostDTO>> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new org.springframework.core.ParameterizedTypeReference<List<ExternalPostDTO>>() {}
        );

        return response.getBody();
    }

    @Transactional
    public UserInformation patchUser(Long id, UserRequestDTO dto) {
        // Find existing user or throw error
        UserInformation existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Partial Update
        if (dto.getTitle() != null) existingUser.setTitle(dto.getTitle());
        if (dto.getFullName() != null) existingUser.setFullName(dto.getFullName());
        if (dto.getEmailAddress() != null) existingUser.setEmailAddress(dto.getEmailAddress());
        if (dto.getMobileNumber() != null) existingUser.setMobileNumber(dto.getMobileNumber());
        if (dto.getHomeAddress() != null) existingUser.setHomeAddress(dto.getHomeAddress());
        if (dto.getPostcode() != null) existingUser.setPostcode(dto.getPostcode());
        if (dto.getDistrict() != null) existingUser.setDistrict(dto.getDistrict());
        if (dto.getState() != null) existingUser.setState(dto.getState());
        if (dto.getCountry() != null) existingUser.setCountry(dto.getCountry());

        // Update Audit Field
        existingUser.setUpdatedBy(99L);

        return repository.save(existingUser);
    }
}
