package br.com.rnconsulting.semfronteiras.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroDTO {

    private String error;

    public ErroDTO(String error){
        this.error = error;
    }
}
