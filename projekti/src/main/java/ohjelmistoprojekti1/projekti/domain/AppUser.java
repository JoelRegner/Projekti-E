package ohjelmistoprojekti1.projekti.domain;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // käyttäjätunnus
    @NotBlank
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String username;

    // salasana hash-muodossa
    @NotBlank
    @Size(min = 6, max = 255)
    private String passwordHash;

    // käyttäjän rooli järjestelmässä
    @NotBlank
    @Pattern(regexp = "ADMIN|LIPUNMYYJÄ|OVITARKASTAJA")
    private String role; // ADMIN / LIPUNMYYJÄ / OVITARKASTAJA

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
