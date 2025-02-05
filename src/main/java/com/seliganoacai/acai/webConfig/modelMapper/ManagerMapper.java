package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.webConfig.dto.createDto.ManagerCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.ManagerResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerMapper {

    public static Manager dtoToManage (ManagerCreateDto dto){

        Manager manager = new Manager();
        manager.setUsername(dto.getUsername());
        manager.setName(dto.getName());
        manager.setPassword(dto.getPassword());

        return manager;
    }

    public static ManagerResponseDto managerToResponseDto(Manager manager){
        ManagerResponseDto dto = new ManagerResponseDto();
        dto.setUsername(manager.getUsername());
        dto.setName(manager.getName());
        dto.setId(manager.getId());
        return dto;
    }

    public static List<ManagerResponseDto> toListDto(List<Manager> managers) {
        return managers.stream().map(ManagerMapper::managerToResponseDto).collect(Collectors.toList());
    }
}
