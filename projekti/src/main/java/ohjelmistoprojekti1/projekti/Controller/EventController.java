package ohjelmistoprojekti1.projekti.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

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

    // GET http://localhost:8080/api/events/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST http://localhost:8080/api/events
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    // PUT http://localhost:8080/api/events/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setName(eventDetails.getName());
            event.setVenue(eventDetails.getVenue());
            event.setCity(eventDetails.getCity());
            event.setStartTime(eventDetails.getStartTime());
            Event updatedEvent = eventRepository.save(event);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE http://localhost:8080/api/events/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET http://localhost:8080/api/events/search?name={name}&city={city}
    @GetMapping("/search")
    public List<Event> searchEvents(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String city) {
        if (name != null && !name.isEmpty()) {
            return eventRepository.findByNameContainingIgnoreCase(name);
        } else if (city != null && !city.isEmpty()) {
            return eventRepository.findByCityContainingIgnoreCase(city);
        } else {
            return eventRepository.findAll();
        }
    }
}
