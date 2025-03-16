package com.seliganoacai.acai.controller;

import com.seliganoacai.acai.entity.Optional;
import com.seliganoacai.acai.repository.OptionalRepository;
import com.seliganoacai.acai.service.OptionalService;
import com.seliganoacai.acai.webConfig.dto.createDto.OptionalCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.OptionalResponseDto;
import com.seliganoacai.acai.webConfig.modelMapper.OptionalMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("acai/v1/options")
public class OptionalController {

    @Autowired
    private OptionalService service;

    @PostMapping
    public ResponseEntity<OptionalResponseDto> create(@RequestBody @Valid OptionalCreateDto dto){
        Optional option = service.create(OptionalMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(OptionalMapper.toDto(option));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionalResponseDto> findById(@PathVariable Long id){
        Optional option = service.findById(id);
        return ResponseEntity.ok().build();
    }

}
