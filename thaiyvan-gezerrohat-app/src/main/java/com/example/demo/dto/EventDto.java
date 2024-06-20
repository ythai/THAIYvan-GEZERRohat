package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotNull
    private String name;
    private String location;
    private String type;
    private Date date;
    private int remainingPlaces;
    private boolean isPaid;
    private double price;
    private UserDto organizer;
    private List<UserDto> participants;
    private List<CommentDto> comments;
}