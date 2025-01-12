package com.seliganoacai.acai.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerCreateDto {

    @NotBlank(message = "Username em brancou ou nulo, gentileza verificar")
    private String username;
    @NotBlank(message = "Password em brancou ou nulo, gentileza verificar")
    private String password;
    @NotBlank(message = "Name em brancou ou nulo, gentileza verificar")
    private String name;
}
