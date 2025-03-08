package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.repository.ProductRepository;
import com.seliganoacai.acai.repository.projectionDto.ProductProjectionDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Transactional
    public Product create(Product product) {
        var valueTotal = product.getQuantity() * product.getValue();
        product.setValueTotal(valueTotal);
        return repository.save(product);
    }
    @Transactional
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Pedido não encontrado"));
    }
    @Transactional
    public Page<ProductProjectionDto> findByPageable(Pageable pageable) {
        return repository.findByPageable(pageable);
    }

    @Transactional
    public Product update(Long id,  String name, String ml,  int quantity, boolean ckeckout,List<String> optionals) {
        Product product;
        product = findById(id);

        if (quantity > product.getQuantity() || quantity < product.getQuantity()){
            var value = quantity * product.getValue();
            product.setValueTotal(value);
        }

        product.setName(name);
        product.setMl(ml);
        product.setQuantity(quantity);
        product.setCkeckout(ckeckout);
        product.setOptionals(optionals);
        return repository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        Product product = findById(id);
        repository.delete(product);
    }
}
