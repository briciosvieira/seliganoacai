package com.seliganoacai.acai.webConfig.configException;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomMessageException {

    private String path;
    private String method;
    private int status;
    private String textStatus;
    private String messageCustom;

    private Map<String, String> errors;

    public CustomMessageException() {}

    public CustomMessageException(HttpServletRequest request, HttpStatus status, String messageCustom, BindingResult result) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.textStatus = status.getReasonPhrase();
        this.messageCustom = messageCustom;

        if (result !=null && result.hasErrors()){
            addErros(result);
        }
    }

    private void addErros(BindingResult result){
        this.errors = new HashMap<>();

        for (FieldError fieldError: result.getFieldErrors()){
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
