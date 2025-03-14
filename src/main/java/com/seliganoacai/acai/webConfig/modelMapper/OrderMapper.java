package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.entity.Orders;
import com.seliganoacai.acai.webConfig.dto.createDto.OrdersCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.OrdersResponseDto;
import org.modelmapper.ModelMapper;

public class OrderMapper {

    public static Orders toOrder(OrdersCreateDto dto){
        return new ModelMapper().map(dto, Orders.class);
    }

    public static OrdersResponseDto toDto(Orders orders){
        return new ModelMapper().map(orders, OrdersResponseDto.class);
    }
}
