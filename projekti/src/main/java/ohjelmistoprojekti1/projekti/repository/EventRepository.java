package ohjelmistoprojekti1.projekti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ohjelmistoprojekti1.projekti.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByNameContainingIgnoreCase(String name);

    List<Event> findByCityContainingIgnoreCase(String city);
    
}

