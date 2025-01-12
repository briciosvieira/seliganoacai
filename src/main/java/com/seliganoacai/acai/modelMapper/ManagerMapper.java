package com.seliganoacai.acai.modelMapper;

import com.seliganoacai.acai.entity.Manager;
import com.seliganoacai.acai.entity.dto.ManagerCreateDto;
import com.seliganoacai.acai.entity.dto.ManagerResponseDto;
import org.modelmapper.ModelMapper;

public class ManagerMapper {

    public static Manager dtoToManage (ManagerCreateDto dto){
        return new ModelMapper().map(dto, Manager.class);
    }

    public static ManagerResponseDto managerToResponseDto(Manager manager){
        return new ModelMapper().map(manager, ManagerResponseDto.class);
    }
}
