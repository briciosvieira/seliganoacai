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
    private List<RelationsOrderProductCreateDto> relationsOrderProductCreateDtos;

    @NotEmpty(message = "A lista de opcionais não pode estar vazia")
    private List<Long> optionals;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  List<RelationsOrderProductCreateDto> getRelationsOrderProductCreateDtos() {
        return relationsOrderProductCreateDtos;
    }

    public void setRelationsOrderProductCreateDtos(List<RelationsOrderProductCreateDto> relationsOrderProductCreateDtos) {
        this.relationsOrderProductCreateDtos = relationsOrderProductCreateDtos;
    }

    public List<Long> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<Long> optionals) {
        this.optionals = optionals;
    }


}
