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
import org.springframework.web.multipart.MultipartFile;

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
    public Product create(String description, double value, MultipartFile image) throws IOException {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto é obrigatória.");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero.");
        }
        Product product = new Product();
        product.setDescription(description);
        product.setValue(value);

        if ( image !=null && !image.isEmpty()){
            String imageUrl = firebaseService.upload(image, "products/" + UUID.randomUUID());
            product.setImageUrl(imageUrl);
        }

        var  savedProduct = repository.save(product);
        return savedProduct;
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
