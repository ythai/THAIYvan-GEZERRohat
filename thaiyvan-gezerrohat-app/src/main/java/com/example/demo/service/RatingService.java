package com.example.demo.service;

import com.example.demo.dto.RatingDto;
import com.example.demo.entity.Rating;
import com.example.demo.mapper.RatingMapper;
import com.example.demo.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }
    @Cacheable(value = "getAllRatings")
    public List<RatingDto> getAllRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }
    @Cacheable(value = "getRatingById")
    public RatingDto getRatingById(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        return ratingMapper.toDto(rating);
    }
    @Caching(
            evict = @CacheEvict(value = "getAllRatings", allEntries = true))
    public RatingDto createRating(RatingDto ratingDto) {
        Rating rating = ratingMapper.toEntity(ratingDto);
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toDto(savedRating);
    }
    @Caching(put = @CachePut(value = "getRatingById"),
            evict = @CacheEvict(value = "getAllRatings", allEntries = true))
    public RatingDto updateRating(Long id, RatingDto ratingDto) {
        ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        Rating rating = ratingMapper.toEntity(ratingDto);
        rating.setId(id);
        Rating updatedRating = ratingRepository.save(rating);
        return ratingMapper.toDto(updatedRating);
    }

    @Caching(put = @CachePut(value = "getRatingById"),
            evict = @CacheEvict(value = "getAllRatings", allEntries = true))
    public void deleteRating(Long id) {
        ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        ratingRepository.deleteById(id);
    }
}