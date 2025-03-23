package com.seliganoacai.acai.webConfig.dto.responseDto;

public class RelationsOrdersProductResponseDto {

    private Long id;
    private ProductResponseDto product;
    private int quantity;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
