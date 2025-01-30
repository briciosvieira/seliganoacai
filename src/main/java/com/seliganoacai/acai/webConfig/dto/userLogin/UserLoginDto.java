package com.seliganoacai.acai.webConfig.dto.userLogin;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank
    @Size(min = 3, max = 20, message = "login deve conter no máximo 20 ")
    private String username;
    @NotBlank
    @Size(min = 5, max = 20, message ="A senha deve conter no mínimo 6 caracter e no máximo 20")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
