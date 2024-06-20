package com.example.demo.controller;

import com.example.demo.dto.InterestDto;
import com.example.demo.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InterestController {

    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @GetMapping
    public ResponseEntity<List<InterestDto>> getAllInterests() {
        List<InterestDto> interests = interestService.getAllInterests();
        return ResponseEntity.ok(interests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestDto> getInterestById(@PathVariable Long id) {
        InterestDto interest = interestService.getInterestById(id);
        return ResponseEntity.ok(interest);
    }

    @PostMapping
    public ResponseEntity<InterestDto> createInterest(@RequestBody InterestDto interestDto) {
        InterestDto createdInterest = interestService.createInterest(interestDto);
        return ResponseEntity.ok(createdInterest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterestDto> updateInterest(@PathVariable Long id, @RequestBody InterestDto interestDto) {
        InterestDto updatedInterest = interestService.updateInterest(id, interestDto);
        return ResponseEntity.ok(updatedInterest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterest(@PathVariable Long id) {
        interestService.deleteInterest(id);
        return ResponseEntity.noContent().build();
    }
}