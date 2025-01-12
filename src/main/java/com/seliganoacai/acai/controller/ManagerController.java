package com.seliganoacai.acai.controller;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.entity.dto.ManagerCreateDto;
import com.seliganoacai.acai.entity.dto.ManagerResponseDto;
import com.seliganoacai.acai.modelMapper.ManagerMapper;
import com.seliganoacai.acai.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("acai/users")
@RequiredArgsConstructor
public class ManagerController {
    @Autowired
    private ManagerService service;

    @PostMapping
    public ResponseEntity<ManagerResponseDto> create (@Valid @RequestBody ManagerCreateDto dto){
        System.out.println("Criando manager: " + dto);
        Manager manager = service.save(ManagerMapper.dtoToManage(dto));
        System.out.println("Manager salvo: " + manager);
       return ResponseEntity.status(HttpStatus.CREATED).body(ManagerMapper.managerToResponseDto(manager));
    }


}
