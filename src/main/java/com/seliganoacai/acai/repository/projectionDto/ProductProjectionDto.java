package com.seliganoacai.acai.repository.projectionDto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductProjectionDto {

    String getName();
    String getMl();
    int getQuantity();
    double getValue();
    double getValueTotal();
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime getDate_create();
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    LocalDateTime getDate_update();
    List<String> getOptionals();
}
