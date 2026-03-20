package ohjelmistoprojekti1.projekti.dto;

import jakarta.validation.constraints.*;

public class CreateUserRequest {

    @NotBlank(message = "Käyttäjätunnus vaaditaan")
    @Size(min = 3, max = 50, message = "Käyttäjätunnus täytyy olla 3-50 merkkiä pitkä")
    private String username;

    @NotBlank(message = "Salasana-hash vaaditaan")
    @Size(min = 6, max = 255, message = "Salasana-hash täytyy olla vähintään 6 merkkiä pitkä")
    private String passwordHash; // testiin, ei oikea hashaus tässä

    @NotBlank(message = "Rooli vaaditaan")
    @Pattern(regexp = "ADMIN|LIPUNMYYJÄ|OVITARKASTAJA", message = "Rooli täytyy olla joko ADMIN, LIPUNMYYJÄ tai OVITARKASTAJA")
    private String role; // ADMIN / LIPUNMYYJÄ / OVITARKASTAJA

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
