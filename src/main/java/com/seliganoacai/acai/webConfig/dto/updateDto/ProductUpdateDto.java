package com.seliganoacai.acai.webConfig.dto.updateDto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDto {

    @Size(min = 3, max = 20)
    private String name;
    @Size(min = 1, max = 100)
    private int quantity;

    public @Size(min = 3, max = 20) String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 20) String name) {
        this.name = name;
    }


    @Size(min = 1, max = 100)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Size(min = 1, max = 100) int quantity) {
        this.quantity = quantity;
    }

}
