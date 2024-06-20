package com.example.demo.service;

import com.example.demo.dto.InterestDto;
import com.example.demo.entity.Interest;
import com.example.demo.mapper.InterestMapper;
import com.example.demo.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterestService {

    private final InterestRepository interestRepository;
    private final InterestMapper interestMapper;

    @Autowired
    public InterestService(InterestRepository interestRepository, InterestMapper interestMapper) {
        this.interestRepository = interestRepository;
        this.interestMapper = interestMapper;
    }

    public List<InterestDto> getAllInterests() {
        List<Interest> interests = interestRepository.findAll();
        return interests.stream()
                .map(interestMapper::toDto)
                .collect(Collectors.toList());
    }

    public InterestDto getInterestById(Long id) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interest not found with id: " + id));
        return interestMapper.toDto(interest);
    }

    public InterestDto createInterest(InterestDto interestDto) {
        Interest interest = interestMapper.toEntity(interestDto);
        Interest savedInterest = interestRepository.save(interest);
        return interestMapper.toDto(savedInterest);
    }

    public InterestDto updateInterest(Long id, InterestDto interestDto) {
        interestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interest not found with id: " + id));
        Interest interest = interestMapper.toEntity(interestDto);
        interest.setId(id);
        Interest updatedInterest = interestRepository.save(interest);
        return interestMapper.toDto(updatedInterest);
    }

    public void deleteInterest(Long id) {
        interestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interest not found with id: " + id));
        interestRepository.deleteById(id);
    }
}
