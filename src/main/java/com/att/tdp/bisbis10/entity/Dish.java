package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dish {
    private @Id
    @GeneratedValue Long id;
    private String name;
    private String description;
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "dish", cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItems;

    public Dish(){}

    public Dish(String name, String description, int price, Restaurant restaurant){
        this.name = name;
        this.description=description;
        this.price=price;
        this.restaurant=restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
