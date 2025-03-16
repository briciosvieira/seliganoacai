package com.seliganoacai.acai.webConfig.dto.responseDto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
public class ManagerResponseDto {


    private String username;
    private String name;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
