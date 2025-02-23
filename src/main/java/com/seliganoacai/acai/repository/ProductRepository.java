package com.seliganoacai.acai.repository;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.repository.projectionDto.ProductProjectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p")
    Page<ProductProjectionDto> findByPageable(Pageable pageable);

}
