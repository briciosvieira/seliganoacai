package com.seliganoacai.acai.controller;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.webConfig.dto.createDto.ManagerCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.ManagerResponseDto;
import com.seliganoacai.acai.webConfig.dto.updateDto.ManagerUpdateDto;
import com.seliganoacai.acai.webConfig.modelMapper.ManagerMapper;
import com.seliganoacai.acai.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("acai/v1/users")
public class ManagerController {
    @Autowired
    private ManagerService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ManagerResponseDto> create (@Valid @RequestBody ManagerCreateDto dto){
        Manager manager = service.save(ManagerMapper.dtoToManage(dto));
       return ResponseEntity.status(HttpStatus.CREATED).body(ManagerMapper.managerToResponseDto(manager));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ManagerResponseDto>> getAll(){
        List<Manager> managers = service.findAll();
        return ResponseEntity.ok(ManagerMapper.toListDto(managers));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponseDto> findById(@PathVariable Long id){
        Manager manager = service.findById(id);
        return ResponseEntity.ok(ManagerMapper.managerToResponseDto(manager));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerResponseDto> update(@PathVariable Long id, @Valid @RequestBody ManagerUpdateDto dto){
        Manager manager = service.updateManager(id, dto.getName(), dto.getPassword(), dto.getUsername() );
        return ResponseEntity.ok().body(ManagerMapper.managerToResponseDto(manager));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       service.findById(id);
       service.delete(id);
       return ResponseEntity.noContent().build();
    }
}
