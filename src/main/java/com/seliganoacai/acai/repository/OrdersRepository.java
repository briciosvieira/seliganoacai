package com.seliganoacai.acai.repository;

import com.seliganoacai.acai.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
