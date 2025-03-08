package com.seliganoacai.acai.controller;

import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.repository.projectionDto.ProductProjectionDto;
import com.seliganoacai.acai.service.ProductService;
import com.seliganoacai.acai.webConfig.dto.createDto.ProductCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.PageableDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.ProductResponseDto;
import com.seliganoacai.acai.webConfig.dto.updateDto.ProductUpdateDto;
import com.seliganoacai.acai.webConfig.modelMapper.PageableMapper;
import com.seliganoacai.acai.webConfig.modelMapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<PageableDto> findAll(@PageableDefault(size = 5, sort = "id")Pageable pageable){
        Page<ProductProjectionDto> product = service.findByPageable(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update( @PathVariable Long id, @Valid @RequestBody ProductUpdateDto dto){
        Product product = service.update(id, dto.getName(), dto.getMl(), dto.getQuantity(), dto.isCkeckout(), dto.getOptionals());
        return ResponseEntity.ok(ProductMapper.entityToResponseDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        findById(id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
