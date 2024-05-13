package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.DishDTO;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restId}/dishes")
public class DishController {
    @Autowired
    private DishService service;

    @PostMapping
    ResponseEntity<?> addDish(@PathVariable Long restId, @RequestBody DishDTO dishDto){
        Dish dish = service.addDish(restId, dishDto);
        if(dish != null) return ResponseEntity.ok(null);
        return ResponseEntity.status(500).body("Error while adding a dish");
    }

    @PutMapping("/{dishId}")
    ResponseEntity<?> updateDish(@PathVariable Long dishId, @RequestBody DishDTO dishDto){
        service.updateDish(dishId, dishDto);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{dishId}")
    ResponseEntity<?> deleteDish(@PathVariable Long dishId){
        service.deleteDish(dishId);
        return ResponseEntity.status(204).body(null);
    }

    @GetMapping
    ResponseEntity<List<Dish>> getDishesByRestaurantId(@PathVariable Long restId){
        return ResponseEntity.ok(service.getDishesByRestaurantId(restId));
    }

}
