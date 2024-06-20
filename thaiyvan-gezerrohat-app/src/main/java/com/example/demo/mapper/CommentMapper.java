package com.example.demo.mapper;

import com.example.demo.mapper.UserMapper;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);
    List<CommentDto> toDtos(List<Comment> comments);
    List<Comment> toEntities(List<CommentDto> commentDtos);
}