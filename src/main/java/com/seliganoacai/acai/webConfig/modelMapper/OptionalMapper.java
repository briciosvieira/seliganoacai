package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.entity.Optional;
import com.seliganoacai.acai.webConfig.dto.createDto.OptionalCreateDto;
import com.seliganoacai.acai.webConfig.dto.responseDto.OptionalResponseDto;
import org.modelmapper.ModelMapper;


public class OptionalMapper {

    public static Optional toEntity (OptionalCreateDto dto){
        return new ModelMapper().map(dto, Optional.class);
    }

    public static OptionalResponseDto toDto(Optional optional){
        return new ModelMapper().map(optional, OptionalResponseDto.class);
    }
}
