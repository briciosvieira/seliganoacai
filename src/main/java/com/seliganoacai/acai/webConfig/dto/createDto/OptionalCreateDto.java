package com.seliganoacai.acai.webConfig.dto.createDto;



public class OptionalCreateDto {
    private Long id;
    private String optional;

    public OptionalCreateDto(String optional) {
        this.optional = optional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }
}
