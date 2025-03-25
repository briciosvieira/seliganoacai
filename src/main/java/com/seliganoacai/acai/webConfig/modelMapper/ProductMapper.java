package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.webConfig.dto.createDto.ProductCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.ProductResponseDto;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product dtoToEntity (ProductCreateDto dto){
        Product product = new  ModelMapper().map(dto, Product.class);
        product.setDescription(dto.getDescription());
        product.setValue(dto.getValue());
        product.setImageUrl(product.getImageUrl());
        return product;

    }

    public static ProductResponseDto entityToResponseDto (Product product){
        ProductResponseDto responseDto = new ModelMapper().map(product, ProductResponseDto.class);
        responseDto.setImageUrl(product.getImageUrl());
        return responseDto;
    }
}
