package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.entity.Order;
import com.seliganoacai.acai.webConfig.dto.createDto.OrderCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.OrderResponseDto;
import org.modelmapper.ModelMapper;

public class OrderMapper {

    public static Order toOrder(OrderCreateDto dto){
        return new ModelMapper().map(dto, Order.class);
    }

    public static OrderResponseDto toDto(Order order){
        return new ModelMapper().map(order, OrderResponseDto.class);
    }
}
