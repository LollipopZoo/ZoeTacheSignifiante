package com.zoe.zoeng.service;

import com.zoe.zoeng.model.Event;
import com.zoe.zoeng.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository){this.eventRepository = eventRepository;}

    public List<Event>getAllEvents() {return eventRepository.findAll();}

    public void addEvent(Event event){eventRepository.save(event);}

    public Event getEventById(int id){return eventRepository.findById(id).orElse(null);}

    public void updateEvent(Event event){
        eventRepository.save(event);
    }

    public void deleteEvent(int id){eventRepository.deleteById(id);}
}
