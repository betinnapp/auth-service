package com.betinnapp.userservice.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(nullable = false)
    private UUID id;

    @NotEmpty(message = "MISSING_LAST_NAME")
    @Column
    private String firstName;

    @NotEmpty(message = "MISSING_LAST_NAME")
    @Column
    private String lastName;

    @NotEmpty(message = "MISSING_EMAIL")
    @Column
    private String email;

    @NotEmpty(message = "MISSING_PASSWORD")
    @Column
    private String password;

    @NotNull(message = "MISSING_BIRTH_DATE")
    @Column
    private Date birthDate;

    @NotNull(message = "MISSING_INITIAL_SCORE")
    @Column
    private BigDecimal initialScore;

    @Column
    private String preferences;

    @Column
    private UUID token;

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getInitailScore() {
        return initialScore;
    }

    public void setInitailScore(BigDecimal initailScore) {
        this.initialScore = initailScore;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
