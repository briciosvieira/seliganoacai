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
        try {
        var valueTotal = product.getQuantity() * product.getValue();
        product.setValueTotal(valueTotal);
        return repository.save(product);

        } catch (RuntimeException e) {
            throw new RuntimeException("Não foi possível adicionar o produto, tente novamente, caso o erro persista, entre em contato com o Administrador.");
        }
    }
    @Transactional
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Pedido não encontrado!"));
    }
    @Transactional
    public Page<ProductProjectionDto> findByPageable(Pageable pageable) {
        try {
        return repository.findByPageable(pageable);

        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Error na paginação dos dados, contact o Administrador.");
        }
    }

    @Transactional
    public Product update(Long id,  String name, String ml,  int quantity, boolean ckeckout,List<String> optionals) {

        try {

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

        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Não foi possível editar o produto, tente novamente ou contact o Administrador.");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {

        Product product = findById(id);
        repository.delete(product);

        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Talvez o produto informado não exista mais no banco de dados, tente novamente ou contact o Administrador.");
        }
    }
}
