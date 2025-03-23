package com.seliganoacai.acai.webConfig.dto.responseDto;


import java.util.List;

public class OrdersResponseDto {


    private String numberOrder;
    private String name;
    private double totalValue;
    private List<RelationsOrdersProductResponseDto> relacionamentOrdersProducts;
    private List<OptionalResponseDto> opcionals;


    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public List<RelationsOrdersProductResponseDto> getRelacionamentOrdersProducts() {
        return relacionamentOrdersProducts;
    }

    public void setRelacionamentOrdersProducts(List<RelationsOrdersProductResponseDto> relacionamentOrdersProducts) {
        this.relacionamentOrdersProducts = relacionamentOrdersProducts;
    }

    public List<OptionalResponseDto> getOpcionals() {
        return opcionals;
    }

    public void setOpcionals(List<OptionalResponseDto> opcionals) {
        this.opcionals = opcionals;
    }
}
