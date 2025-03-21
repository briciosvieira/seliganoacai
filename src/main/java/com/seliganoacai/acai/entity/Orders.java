package com.seliganoacai.acai.entity;

import com.seliganoacai.acai.entity.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String numberOrder;

    @Column(nullable = false)
    private double totalValue;

    @Enumerated(EnumType.STRING)
    private StatusOrder status = StatusOrder.EM_PREPARACAO;

    @ManyToMany
    private List<Optional> opcionals = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelacionamentOrdersProduct> relacionamentOrdersProducts = new ArrayList<>();


    public double calculateTotalValue() {
        this.totalValue = relacionamentOrdersProducts.stream()
                .mapToDouble(x -> x.getProduct().getValue() * x.getQuantity())
                .sum();
        return this.totalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id);
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

    public List<RelacionamentOrdersProduct> getRelacionamentOrdersProducts() {
        return relacionamentOrdersProducts;
    }

    public void setRelacionamentOrdersProducts(List<RelacionamentOrdersProduct> relacionamentOrdersProducts) {
        this.relacionamentOrdersProducts = relacionamentOrdersProducts;
    }

    public List<Optional> getOpcionals() {
        return opcionals;
    }

    public void setOpcionals(List<Optional> opcionals) {
        this.opcionals = opcionals;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }
}
