package br.com.rnconsulting.semfronteiras.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class RequestUsuarioCadastrarDTO {

    private String email;
    private String senha;
    private String situacao;

    }

