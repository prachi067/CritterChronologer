package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customer")
public class Customer{

    @Id
    @GeneratedValue
    private long id;

    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Customer() {
    }

    public Customer(long id, String name, String phoneNumber, String notes, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}