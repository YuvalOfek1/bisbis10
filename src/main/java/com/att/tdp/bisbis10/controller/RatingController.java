package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RatingDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RatingService;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    RestaurantService restService;
    @Autowired
    RatingService ratingService;

    @PostMapping
    ResponseEntity<?> addRating(@RequestBody RatingDTO rating){
        ratingService.addRating(rating);
        return ResponseEntity.ok(null);
    }


}
