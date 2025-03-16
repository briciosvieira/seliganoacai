package com.seliganoacai.acai.webConfig.dto.createDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.List;

public class OrdersCreateDto {

    @Size(min = 3, max = 40, message = "O nome deve ter entre 3 e 40 caracteres")
    @NotBlank(message = "O cliente deve informar o nome")
    private String name;

    @NotEmpty(message = "A lista de produtos não pode estar vazia")
    private List<Long> products; // IDs dos produtos

    @NotEmpty(message = "A lista de opcionais não pode estar vazia")
    private List<Long> optionals; // IDs dos opcionais

    // Getters e Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public List<Long> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<Long> optionals) {
        this.optionals = optionals;
    }
}
