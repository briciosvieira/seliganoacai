package com.seliganoacai.acai.webConfig.dto.createDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.List;

public class OrdersCreateDto {


    @Size(min = 3, max = 40, message = "O nome deve ter entre 3 e 40 caracteres")
    @NotBlank(message = "O cliente deve informar o nome")
    private String name;

    /*
    Uma pergunta que eu vou me fazer no futuro, porque não colocar Long pra buscar o ID, eu não coloquei long
    porque na hora de colocar a quantidade não iria inserir no banco e não coloquei separado porque não funcionaria,
    porque a variavel quantity  é da classe RelationsOerderProduct, então criei um dto que tem long e tem quantidade
    que e assim eu consigo substituir a lista de long colocando o dto, podendo inserir o id do item e também podendo inserir
    a quantidade do produto.
    */

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
