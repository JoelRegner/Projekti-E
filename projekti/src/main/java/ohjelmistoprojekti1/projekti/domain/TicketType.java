package ohjelmistoprojekti1.projekti.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // lipputyypin kuvaus (esim. Aikuinen, Lapsi, Opiskelija)
    private String description;

    // price kuuluu TicketTypeen, ei Eventiin
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "event_id")
    // FK: event_id -> Event.id
    // monta TicketTypea voi kuulua yhteen Eventiin
    
    private Event event;

    @OneToMany(mappedBy = "ticketType")
    private List<Ticket> tickets;

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
