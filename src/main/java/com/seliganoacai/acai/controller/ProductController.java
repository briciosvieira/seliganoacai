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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("acai/v1/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> create ( @RequestParam("description") String description,
                                                       @RequestParam("value") double value,
                                                       @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

            ProductCreateDto productDto = new ProductCreateDto();
            productDto.setDescription(description);
            productDto.setValue(value);
            productDto.setImageUrl(image);

        Product product = service.create(productDto);
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

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> update( @PathVariable Long id,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("value") double value,
                                                      @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {


        ProductCreateDto dto = new ProductCreateDto();
        dto.setDescription(description);
        dto.setValue(value);
        dto.setImageUrl(image);

        Product updatedProduct = service.update(id, dto);
        return ResponseEntity.ok(ProductMapper.entityToResponseDto(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        findById(id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
