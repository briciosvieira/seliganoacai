package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Product create(Product product) {
        var valueTotal = product.getQuantity() * product.getValue();
        product.setValueTotal(valueTotal);
        return repository.save(product);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Pedido não encontrado"));
    }
}
