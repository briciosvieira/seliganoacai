package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.webConfig.dto.ProductCreateDto;
import com.seliganoacai.acai.webConfig.dto.ProductResponseDto;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product dtoToEntity (ProductCreateDto dto){
        Product product = new  ModelMapper().map(dto, Product.class);
        product.setOptionals(dto.getOptionals());
        return product;
    }

    public static ProductResponseDto entityToResponseDto (Product product){
        return new ModelMapper().map(product, ProductResponseDto.class);
    }
}
