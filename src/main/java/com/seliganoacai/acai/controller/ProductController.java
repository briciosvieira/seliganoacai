package com.seliganoacai.acai.controller;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.service.ProductService;
import com.seliganoacai.acai.webConfig.dto.createDto.ProductCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.ProductResponseDto;
import com.seliganoacai.acai.webConfig.modelMapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("acai/v1/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create (@Valid @RequestBody ProductCreateDto dto){
        Product product = service.create(ProductMapper.dtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.entityToResponseDto(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id){
        Product product = service.findById(id);
        return ResponseEntity.ok(ProductMapper.entityToResponseDto(product));
    }

}
