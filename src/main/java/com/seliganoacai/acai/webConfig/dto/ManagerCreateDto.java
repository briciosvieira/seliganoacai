package com.seliganoacai.acai.webConfig.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
public class ManagerCreateDto {


    @NotBlank(message = "Username em brancou ou nulo, gentileza verificar")
    private String username;

    @NotBlank(message = "Name em brancou ou nulo, gentileza verificar")
    private String name;


    @NotBlank(message = "Password em brancou ou nulo, gentileza verificar")
    private String password;



    public @NotBlank(message = "Username em brancou ou nulo, gentileza verificar") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username em brancou ou nulo, gentileza verificar") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Name em brancou ou nulo, gentileza verificar") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name em brancou ou nulo, gentileza verificar") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Password em brancou ou nulo, gentileza verificar") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password em brancou ou nulo, gentileza verificar") String password) {
        this.password = password;
    }
}
