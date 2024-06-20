package com.example.demo.mapper;

import com.example.demo.dto.InterestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Interest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface InterestMapper {
    InterestDto toDto(Interest interest);
    Interest toEntity(InterestDto interestDto);
    List<InterestDto> toDtos(List<Interest> interests);
    List<Interest> toEntities(List<InterestDto> interestDtos);
}