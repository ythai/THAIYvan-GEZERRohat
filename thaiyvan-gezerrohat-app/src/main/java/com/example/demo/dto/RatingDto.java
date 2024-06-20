package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private int stars;
    private String review;
    private UserDto reviewer;
}