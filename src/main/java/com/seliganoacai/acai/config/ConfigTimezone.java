package com.seliganoacai.acai.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class ConfigTimezone {

    @PostConstruct
    public void configTimezone(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bahia"));
    }
}
