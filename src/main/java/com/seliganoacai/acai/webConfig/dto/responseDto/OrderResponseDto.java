package com.seliganoacai.acai.webConfig.dto.responseDto;


import java.util.List;

public class OrderResponseDto {
    private String name;
    private List<String> produtos;
    private List<String> opcionais;

    public String getName() {
        return name;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    public List<String> getOpcionais() {
        return opcionais;
    }
}
