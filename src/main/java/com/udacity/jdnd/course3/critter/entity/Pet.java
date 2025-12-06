package com.udacity.jdnd.course3.critter.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="pet")
public class Pet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;
    private LocalDate birthDate;
    private String notes;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    //getters ans setters


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public PetType getType() {
        return type;
    }
    public void setType(PetType type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public String getNotes() {

        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
