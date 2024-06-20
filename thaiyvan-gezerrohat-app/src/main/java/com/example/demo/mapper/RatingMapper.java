package com.example.demo.mapper;

import com.example.demo.dto.RatingDto;
import com.example.demo.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface RatingMapper {
    RatingDto toDto(Rating rating);
    Rating toEntity(RatingDto ratingDto);
    List<RatingDto> toDtos(List<Rating> ratings);
    List<Rating> toEntities(List<RatingDto> ratingDtos);
}