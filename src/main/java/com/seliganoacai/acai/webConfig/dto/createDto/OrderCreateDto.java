package com.seliganoacai.acai.webConfig.dto.createDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.util.List;

public class OrderCreateDto {

    @NotBlank(message = "O Cliente deve informar o nome")
    @Size(min = 3, max = 40)
    private String name;
    @NotBlank(message = "Campo para pedidos deve ser preenchido")
    private List<String> produtos;
    @NotBlank(message = "O opcional deve ser preenchido")
    private List<String> opcionais;

    public @NotBlank @Size(min = 3, max = 40) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 3, max = 40) String name) {
        this.name = name;
    }

    public @NotBlank List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(@NotBlank List<String> produtos) {
        this.produtos = produtos;
    }

    public @NotBlank List<String> getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(@NotBlank List<String> opcionais) {
        this.opcionais = opcionais;
    }
}
