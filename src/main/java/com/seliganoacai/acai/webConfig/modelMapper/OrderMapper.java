package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.entity.Orders;
import com.seliganoacai.acai.webConfig.dto.createDto.OrdersCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.OptionalResponseDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.OrdersResponseDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.ProductResponseDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.RelationsOrdersProductResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Orders toEntity(OrdersCreateDto dto) {
        Orders orders = new Orders();
        orders.setName(dto.getName());
        return orders;
    }

    public static OrdersResponseDto toDto(Orders orders) {
        OrdersResponseDto dto = new OrdersResponseDto();
        dto.setName(orders.getName());
        dto.setNumberOrder(orders.getNumberOrder());
        dto.setTotalValue(orders.getTotalValue());

        // Mapeia os relacionamentos (produtos)
        List<RelationsOrdersProductResponseDto> listRelationsDtos = orders.getRelationsOrdersProducts().stream()
                .map(filterRelations -> {
                    RelationsOrdersProductResponseDto relationsDto = new RelationsOrdersProductResponseDto();
                    relationsDto.setId(filterRelations.getId());
                    relationsDto.setQuantity(filterRelations.getQuantity());

                    // Mapeia o produto
                    ProductResponseDto productDto = new ProductResponseDto();
                    productDto.setDescription(filterRelations.getProduct().getDescription());
                    productDto.setValue(filterRelations.getProduct().getValue());
                    relationsDto.setProduct(productDto);

                    return relationsDto;
                })
                .collect(Collectors.toList());
        dto.setRelacionamentOrdersProducts(listRelationsDtos);

        // Mapeia os opcionais
        List<OptionalResponseDto> optionalDtos = orders.getOpcionals().stream()
                .map(optional -> {
                    OptionalResponseDto optionalDto = new OptionalResponseDto();
                    optionalDto.setId(optional.getId());
                    optionalDto.setOptional(optional.getOptional());
                    return optionalDto;
                })
                .collect(Collectors.toList());
        dto.setOpcionals(optionalDtos);

        return dto;
    }
}
