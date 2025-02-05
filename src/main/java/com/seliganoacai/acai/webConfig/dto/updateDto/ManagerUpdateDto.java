package com.seliganoacai.acai.webConfig.dto.updateDto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagerUpdateDto {

    @Size(min = 5, max = 20)
    private String username;
    @Size(min = 5, max = 20)
    private String name;
    @Size(min = 6, max = 20)
    private String password;

    public @Size(min = 5, max = 20) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 5, max = 20) String username) {
        this.username = username;
    }

    public @Size(min = 5, max = 20) String getName() {
        return name;
    }

    public void setName(@Size(min = 5, max = 20) String name) {
        this.name = name;
    }

    public @Size(min = 6, max = 20) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20) String password) {
        this.password = password;
    }
}
