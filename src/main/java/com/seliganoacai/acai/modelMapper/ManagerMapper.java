package com.seliganoacai.acai.modelMapper;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.entity.dto.ManagerCreateDto;
import com.seliganoacai.acai.entity.dto.ManagerResponseDto;

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
}
