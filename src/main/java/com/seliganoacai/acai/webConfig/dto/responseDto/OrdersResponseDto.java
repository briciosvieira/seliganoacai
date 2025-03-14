package com.seliganoacai.acai.webConfig.dto.responseDto;


import com.seliganoacai.acai.entity.Product;

import java.util.List;

public class OrdersResponseDto {
    private String name;
    private List<Product> products;
    private List<String> opcionais;

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<String> getOpcionais() {
        return opcionais;
    }
}
