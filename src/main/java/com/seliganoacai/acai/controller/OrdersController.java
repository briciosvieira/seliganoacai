package com.seliganoacai.acai.controller;


import com.seliganoacai.acai.entity.Orders;
import com.seliganoacai.acai.service.OrdersService;
import com.seliganoacai.acai.webConfig.dto.createDto.OrdersCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.OrdersResponseDto;
import com.seliganoacai.acai.webConfig.modelMapper.OrderMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("acai/v1/orders")
public class OrdersController {

    @Autowired
    private OrdersService service;

    @PostMapping
    public ResponseEntity<OrdersResponseDto> create(@Valid @RequestBody OrdersCreateDto dto){
        Orders orders = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderMapper.toDto(orders));
    }
}
