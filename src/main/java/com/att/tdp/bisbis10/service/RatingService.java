package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RatingDTO;
import com.att.tdp.bisbis10.dto.RestaurantDTO;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    RestaurantService restaurantService;

    public void addRating(RatingDTO rating) {
        if(!isRatingValid(rating.rating())) return;
        Restaurant rest = restaurantService.getRestaurantById(rating.restaurantId()).orElse(null);
        if (rest == null) return;
        Rating r = new Rating();
        r.setRating(rating.rating());
        r.setRestaurant(rest);
        ratingRepository.save(r);

//        editRestaurantAvgRating(rating.restaurantId());
    }

    private boolean isRatingValid(Float rating){
        return rating >= 1 && rating <= 5;
    }

//    private void editRestaurantAvgRating(Long restId){
//        DecimalFormat decimalFormat = new DecimalFormat("0.##");
//        Float avgRating = Float.valueOf(decimalFormat.format(ratingRepository.getAverageRating(restId)));
//        restaurantService.addRating(restId, avgRating);
//    }

//    public void deleteByRestaurantId(Long restId){
//        ratingRepository.deleteByRestaurantId(restId);
//    }
}
