package com.att.tdp.bisbis10.service;


import com.att.tdp.bisbis10.dto.OrderDTO;
import com.att.tdp.bisbis10.dto.OrderItemDTO;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Orders;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {
    @Autowired
    OrderRepository repository;
    @Autowired
    RestaurantService restService;
    @Autowired
    DishService dishService;

    public Orders createOrder(OrderDTO orderDto){
        Orders order = new Orders();
        Restaurant rest = restService.getRestaurantById(orderDto.restaurantId()).orElse(null);
        if(rest==null) return null;
        order.setRestaurant(rest);

        List<OrderItem> orderItemList = new ArrayList<>();
        List<Long> restDishes = rest.getDishes().stream().map(Dish::getId).toList();
        for(OrderItemDTO orderItemDto : orderDto.orderItems() ){
            if(orderItemDto.dishId() == null || !restDishes.contains(orderItemDto.dishId())) return null;

            OrderItem orderItem = orderItemFromOrderItemDto(orderItemDto);
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
        order.setOrderItems(orderItemList);
        return repository.save(order);

    }

    public OrderItem orderItemFromOrderItemDto(OrderItemDTO orderItemDto){
        OrderItem orderItem = new OrderItem();
        orderItem.setDish(dishService.getDishById(orderItemDto.dishId()).orElse(null));
        orderItem.setAmount(orderItemDto.amount());
        return orderItem;
    }

}
