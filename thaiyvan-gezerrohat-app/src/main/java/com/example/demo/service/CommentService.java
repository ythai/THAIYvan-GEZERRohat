package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Cacheable(value = "getAllComments")
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "getCommentById")
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        return commentMapper.toDto(comment);
    }

    @Caching(
            evict = @CacheEvict(value = "getAllComments", allEntries = true))
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    @Caching(put = @CachePut(value = "getCommentById"),
            evict = @CacheEvict(value = "getAllComments", allEntries = true))
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        Comment comment = commentMapper.toEntity(commentDto);
        comment.setId(id);
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toDto(updatedComment);
    }

    @Caching(put = @CachePut(value = "getCommentById"),
            evict = @CacheEvict(value = "getAllComments", allEntries = true))
    public void deleteComment(Long id) {
        commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        commentRepository.deleteById(id);
    }
}