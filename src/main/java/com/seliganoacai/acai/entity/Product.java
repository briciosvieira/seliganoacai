package com.seliganoacai.acai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String ml;
    @Column
    private int quantity;
    @Column
    private double value;
    @Column
    private double valueTotal;
    @CreatedDate
    private LocalDateTime date_create;
    @LastModifiedDate
    private LocalDateTime date_update;
    @Column
    private boolean ckeckout;

    @ElementCollection
    @CollectionTable(name = "product_optionals", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "optional_name")
    private List<String> optionals = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

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

    public List<String> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<String> optionals) {
        this.optionals = optionals;
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

    public boolean isCkeckout() {
        return ckeckout;
    }

    public void setCkeckout(boolean ckeckout) {
        this.ckeckout = ckeckout;
    }

    public double getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(double valueTotal) {
        this.valueTotal = valueTotal;
    }
}
