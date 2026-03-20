package ohjelmistoprojekti1.projekti.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CreateTicketTypeRequest {

    @NotBlank(message = "Kuvaus ei voi olla tyhjä")
    @Size(min = 2, max = 50, message = "Kuvaus täytyy olla 2-50 merkkiä pitkä")
    private String description;

    @NotNull(message = "Hinta vaaditaan")
    @DecimalMin(value = "0.0", message = "Hinta ei voi olla pienempi kuin 0")
    private BigDecimal price;

    @NotNull(message = "Event ID vaaditaan")
    private Long eventId;

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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
