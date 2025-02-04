package com.seliganoacai.acai.repository;

import com.seliganoacai.acai.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
