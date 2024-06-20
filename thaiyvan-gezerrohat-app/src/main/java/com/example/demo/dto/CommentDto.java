package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String text;
    private UserDto author;
    private EventDto event;
}