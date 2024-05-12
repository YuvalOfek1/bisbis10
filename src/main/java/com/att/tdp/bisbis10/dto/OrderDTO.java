package com.att.tdp.bisbis10.dto;

import java.util.List;

public record OrderDTO(Long restaurantId, List<OrderItemDTO> orderItems) {}
