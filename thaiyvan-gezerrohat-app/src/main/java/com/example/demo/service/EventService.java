package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.entity.Event;
import com.example.demo.mapper.EventMapper;
import com.example.demo.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }
    public EventDto getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return eventMapper.toDto(event);
    }
    public EventDto createEvent(EventDto eventDto) {
        if (eventDto.getName() == null || eventDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Event name must not be null or empty");
        }
        Event event = eventMapper.toEntity(eventDto);
        System.out.println("Event entity before save 2: " + eventDto);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }
    public EventDto updateEvent(Long id, EventDto eventDto) {
        eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        Event event = eventMapper.toEntity(eventDto);
        event.setId(id);
        Event updatedEvent = eventRepository.save(event);
        return eventMapper.toDto(updatedEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        eventRepository.deleteById(id);
    }
}