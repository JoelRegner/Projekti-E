package ohjelmistoprojekti1.projekti.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import ohjelmistoprojekti1.projekti.domain.Event;
import ohjelmistoprojekti1.projekti.repository.EventRepository;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // GET http://localhost:8080/api/events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // POST http://localhost:8080/api/events
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    // DELETE http://localhost:8080/api/events/{id}
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }
}
