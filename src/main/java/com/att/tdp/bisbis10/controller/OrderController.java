package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.OrderDTO;
import com.att.tdp.bisbis10.entity.Orders;
import com.att.tdp.bisbis10.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping
    ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDto){
        Orders order = service.createOrder(orderDto);
        return ResponseEntity.ok(order==null? null : Map.of("orderId", order.getId()));
    }
}
