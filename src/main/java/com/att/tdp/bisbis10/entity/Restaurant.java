package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {
    private @Id @GeneratedValue Long id;
    private String name;
    private boolean isKosher;
    @ElementCollection
    private List<String> cuisines;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating> ratings;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant() {}
//    public Restaurant(String name, boolean is_kosher, List<String> cuisines){
//        this.name=name;
//        this.isKosher = is_kosher;
//        this.cuisines=cuisines;
//    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsKosher() {
        return isKosher;
    }

    public void setIsKosher(boolean is_kosher) {
        this.isKosher = is_kosher;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = new ArrayList<>(cuisines);
    }

    @JsonGetter("averageRating")
    public Double getAverageRating() {
        Double sum = 0.0;
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        if(ratings==null || ratings.size()==0) return sum;
        for(Rating rating: ratings ){
            sum+=rating.getRating();
        }
        return Double.valueOf(decimalFormat.format(sum/ratings.size()));
    }
}
