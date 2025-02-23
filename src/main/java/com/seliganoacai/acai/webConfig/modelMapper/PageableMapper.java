package com.seliganoacai.acai.webConfig.modelMapper;

import com.seliganoacai.acai.webConfig.dto.responseDto.PageableDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class PageableMapper {

    public static PageableDto toDto(Page page){
        return new ModelMapper().map(page, PageableDto.class);
    }
}
