package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.mapper.EventMapper;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "getAllEvents")
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "getEventById")
    public EventDto getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return eventMapper.toDto(event);
    }

    @Caching(
            evict = @CacheEvict(value = "getAllEvents", allEntries = true))
    public EventDto createEvent(EventDto eventDto) {
        if (eventDto.getName() == null || eventDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Event name must not be null or empty");
        }
        User organizer = userRepository.findById(eventDto.getOrganizer().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + eventDto.getOrganizer().getId()));

        Event event = eventMapper.toEntity(eventDto);
        event.setOrganizer(organizer);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }

    @Caching(put = @CachePut(value = "getEventById"),
            evict = @CacheEvict(value = "getAllEvents", allEntries = true))
    public EventDto updateEvent(Long id, EventDto eventDto) {
        eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        Event event = eventMapper.toEntity(eventDto);
        event.setId(id);
        Event updatedEvent = eventRepository.save(event);
        return eventMapper.toDto(updatedEvent);
    }

    @Caching(put = @CachePut(value = "getEventById"),
            evict = @CacheEvict(value = "getAllEvents", allEntries = true))
    public void deleteEvent(Long id) {
        eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        eventRepository.deleteById(id);
    }
}