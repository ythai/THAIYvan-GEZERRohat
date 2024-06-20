package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String city;
    private String region;
    private int age;
    private List<InterestDto> interests;
    private List<RatingDto> ratings;
    private List<CommentDto> comments;
    private List<EventDto> events;
}