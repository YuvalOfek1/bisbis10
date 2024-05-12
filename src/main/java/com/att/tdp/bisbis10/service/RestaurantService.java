package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RestaurantDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    private RestaurantDTO restaurantToDTO_without_dishes(Restaurant restaurant){
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAverageRating(),
                restaurant.getIsKosher(),
                restaurant.getCuisines()
        );
    }

    public List<RestaurantDTO> getAllRestaurants() {
        return repository.findAll().stream().map(this::restaurantToDTO_without_dishes).toList();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return repository.findById(id);
    }

    public List<RestaurantDTO> getRestaurantsByCuisine(String cuisine) {
        return repository.findByCuisine(cuisine).stream().map(this::restaurantToDTO_without_dishes).toList();
    }

    public void createRestaurant(RestaurantDTO newRestaurant) {
        repository.save(restaurantDtoToEntity(newRestaurant)); //TODO: check if works
    }

    public void editRestaurant(Long id, RestaurantDTO newRestaurantDTO) {
        getRestaurantById(id).ifPresent(restaurant -> {
            if (newRestaurantDTO.cuisines() != null) restaurant.setCuisines(newRestaurantDTO.cuisines());
            if (newRestaurantDTO.name() != null) restaurant.setName(newRestaurantDTO.name());
            if (newRestaurantDTO.isKosher() != null) restaurant.setIsKosher(newRestaurantDTO.isKosher());
            repository.save(restaurant);
        });
    }

    public void deleteRestaurant(Long id) {
        repository.deleteById(id);
    }


    public Restaurant restaurantDtoToEntity(RestaurantDTO restaurantDTO){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.name());
        restaurant.setIsKosher(restaurantDTO.isKosher());
        restaurant.setCuisines(restaurantDTO.cuisines());
        return restaurant;
    }

}
