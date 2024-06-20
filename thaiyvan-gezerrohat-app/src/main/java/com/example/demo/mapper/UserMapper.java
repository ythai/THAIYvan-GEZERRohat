package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {InterestMapper.class, RatingMapper.class})
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtos(List<User> users);
    List<User> toEntities(List<UserDto> userDtos);
}