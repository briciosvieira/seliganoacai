package com.seliganoacai.acai.webConfig.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDto {

    private String name;
    private String ml;
    private int quantity;
    private double value;
    private double valueTotal;
    private boolean ckeckout;
    @JsonFormat( pattern = "dd/MM/yyyy")
    private LocalDateTime date_create;
    @JsonFormat( pattern = "dd/MM/yyyy")
    private LocalDateTime date_update;
    private List<String> optionals;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMl() {
        return ml;
    }

    public void setMl(String ml) {
        this.ml = ml;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDate_create() {
        return date_create;
    }

    public void setDate_create(LocalDateTime date_create) {
        this.date_create = date_create;
    }

    public LocalDateTime getDate_update() {
        return date_update;
    }

    public void setDate_update(LocalDateTime date_update) {
        this.date_update = date_update;
    }

    public List<String> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<String> optionals) {
        this.optionals = optionals;
    }

    public double getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(double valueTotal) {
        this.valueTotal = valueTotal;
    }

    public boolean isCkeckout() {
        return ckeckout;
    }

    public void setCkeckout(boolean ckeckout) {
        this.ckeckout = ckeckout;
    }
}
