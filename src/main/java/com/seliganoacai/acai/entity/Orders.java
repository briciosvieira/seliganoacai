package com.seliganoacai.acai.entity;

import com.seliganoacai.acai.entity.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
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

    private String name;

    @ManyToMany
    private List<Product> products;

    @ManyToMany
    private List<Optional> opcionais;

    @Column(nullable = false)
    private double totalValue;

    @ManyToOne
    private Manager manager;

    @Enumerated(EnumType.STRING)
    private StatusOrder status = StatusOrder.EM_PREPARACAO;

    public void calculateTotalValue() {
        this.totalValue = products.stream()
                .mapToDouble(Product::getValue)
                .sum();
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Optional> getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(List<Optional> opcionais) {
        this.opcionais = opcionais;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
