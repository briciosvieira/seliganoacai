package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.repository.ProductRepository;
import com.seliganoacai.acai.repository.projectionDto.ProductProjectionDto;
import com.seliganoacai.acai.webConfig.dto.createDto.ProductCreateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private FirebaseService firebaseService;

    @Transactional
    public Product create(ProductCreateDto dto) throws IOException {

        validationAtributesProductDto(dto.getDescription(), dto.getValue());

        Product product = new Product();
        product.setDescription(dto.getDescription());
        product.setValue(dto.getValue());


        if ( dto.getImageUrl() !=null && !dto.getImageUrl().isEmpty()){
            String imageUrl = firebaseService.upload(dto.getImageUrl(), dto.getImageUrl().getOriginalFilename());
            product.setImageUrl(imageUrl);
        }

        var  savedProduct = repository.save(product);
        return savedProduct;
    }

    private void validationAtributesProductDto(String description, double value){
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto é obrigatória.");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero.");
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
    public Product update(Long id,  String name, int quantity) {

        try {

        Product product;
        product = findById(id);
        product.setDescription(name);
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

    @Transactional
    public List<Product> findByIds(List<Long> productIds) {
        return repository.findAllById(productIds);
    }
}
