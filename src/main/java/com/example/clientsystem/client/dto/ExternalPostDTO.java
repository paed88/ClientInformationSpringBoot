package com.example.clientsystem.client.dto;

import lombok.Data;

@Data
public class ExternalPostDTO {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
