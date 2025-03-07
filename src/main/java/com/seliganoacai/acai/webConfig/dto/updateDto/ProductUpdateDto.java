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
    @Size(min = 2, max = 6)
    private String ml;
    @Size(min = 1, max = 100)
    private int quantity;
    private List<String> optionals;
    private boolean ckeckout;

    public @Size(min = 3, max = 20) String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 20) String name) {
        this.name = name;
    }

    public @Size(min = 2, max = 6) String getMl() {
        return ml;
    }

    public void setMl(@Size(min = 2, max = 6) String ml) {
        this.ml = ml;
    }

    @Size(min = 1, max = 100)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Size(min = 1, max = 100) int quantity) {
        this.quantity = quantity;
    }

    public List<String> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<String> optionals) {
        this.optionals = optionals;
    }

    public boolean isCkeckout() {
        return ckeckout;
    }

    public void setCkeckout(boolean ckeckout) {
        this.ckeckout = ckeckout;
    }
}
