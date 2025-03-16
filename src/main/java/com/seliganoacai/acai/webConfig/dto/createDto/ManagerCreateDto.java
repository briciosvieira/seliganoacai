package com.seliganoacai.acai.webConfig.dto.createDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class ManagerCreateDto {


    @NotBlank(message = "Username em brancou ou nulo, gentileza verificar")
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank(message = "Name em brancou ou nulo, gentileza verificar")
    @Size(min = 4, max = 50)
    private String name;


    @NotBlank(message = "Password em brancou ou nulo, gentileza verificar")
    @Size(min = 4, max = 30)
    private String password;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password) {
        this.password = password;
    }
}
