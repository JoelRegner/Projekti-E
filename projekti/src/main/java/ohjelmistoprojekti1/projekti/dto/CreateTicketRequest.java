package ohjelmistoprojekti1.projekti.dto;

import jakarta.validation.constraints.*;

public class CreateTicketRequest {

    @NotNull(message = "TicketType ID vaaditaan")
    private Long ticketTypeId;

    @NotBlank(message = "Koodi vaaditaan")
    @Size(min = 5, max = 50, message = "Koodin täytyy olla 5-50 merkkiä pitkä")
    private String code;

    @NotBlank(message = "Status vaaditaan")
    @Pattern(regexp = "VALID|USED", message = "Status täytyy olla joko VALID tai USED")
    private String status; // VALID / USED

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
