package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Orders;
import com.seliganoacai.acai.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrdersService {

    @Autowired
    private OrdersRepository repository;

    public Orders create(Orders order) {

    }
}
