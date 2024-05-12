package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OrderRepository  extends JpaRepository<Orders,UUID> {
}
