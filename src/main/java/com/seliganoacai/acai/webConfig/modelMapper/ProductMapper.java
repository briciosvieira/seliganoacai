package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.webConfig.dto.createDto.ProductCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.ProductResponseDto;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product dtoToEntity (ProductCreateDto dto){
        return new  ModelMapper().map(dto, Product.class);
    }

    public static ProductResponseDto entityToResponseDto (Product product){
        return new ModelMapper().map(product, ProductResponseDto.class);
    }
}
