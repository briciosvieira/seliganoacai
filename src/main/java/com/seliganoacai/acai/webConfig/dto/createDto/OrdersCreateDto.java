package com.seliganoacai.acai.webConfig.dto.createDto;
import com.seliganoacai.acai.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.util.List;

public class OrdersCreateDto {

    @NotBlank(message = "O Cliente deve informar o nome")
    @Size(min = 3, max = 40)
    private String name;
    @NotBlank(message = "Campo para pedidos deve ser preenchido")
    private List<Product> products;
    @NotBlank(message = "O opcional deve ser preenchido")
    private List<String> opcionais;

    public @NotBlank @Size(min = 3, max = 40) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 3, max = 40) String name) {
        this.name = name;
    }

    public @NotBlank(message = "Campo para pedidos deve ser preenchido") List<Product> getProducts() {
        return products;
    }

    public void setProducts(@NotBlank(message = "Campo para pedidos deve ser preenchido") List<Product> products) {
        this.products = products;
    }

    public @NotBlank List<String> getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(@NotBlank List<String> opcionais) {
        this.opcionais = opcionais;
    }
}
