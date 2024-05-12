package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RestaurantDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RatingService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @GetMapping
    ResponseEntity<List<?>> getRestaurants(@RequestParam(name = "cuisine", required = false) String cuisine){
        try{
            if(cuisine!=null && !cuisine.isEmpty()){
                return ResponseEntity.ok(service.getRestaurantsByCuisine(cuisine));
            }
            else{
                return ResponseEntity.ok(service.getAllRestaurants());
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getRestaurantById(@PathVariable Long id){
        Optional<Restaurant> restaurant = service.getRestaurantById(id);
        return ResponseEntity.ok(restaurant.orElse(null));
    }

    @PostMapping
    ResponseEntity<?> createRestaurant(@RequestBody RestaurantDTO newRestaurantDTO) {
        service.createRestaurant(newRestaurantDTO);
        return ResponseEntity.status(201).body(null);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> editRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO newRestaurantDTO){
        service.editRestaurant(id, newRestaurantDTO);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRestaurant(@PathVariable Long id){
        try{
            service.deleteRestaurant(id);
            return ResponseEntity.status(204).body(null);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body(null);
        }

    }

}
