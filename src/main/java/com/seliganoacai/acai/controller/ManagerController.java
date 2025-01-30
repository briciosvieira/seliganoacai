package com.seliganoacai.acai.controller;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.webConfig.dto.ManagerCreateDto;
import com.seliganoacai.acai.webConfig.dto.ManagerResponseDto;
import com.seliganoacai.acai.webConfig.modelMapper.ManagerMapper;
import com.seliganoacai.acai.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        Manager manager = service.save(ManagerMapper.dtoToManage(dto));
       return ResponseEntity.status(HttpStatus.CREATED).body(ManagerMapper.managerToResponseDto(manager));
    }


}
