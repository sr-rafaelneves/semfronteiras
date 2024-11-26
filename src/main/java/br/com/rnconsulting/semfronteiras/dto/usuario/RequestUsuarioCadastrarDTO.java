package br.com.rnconsulting.semfronteiras.dto.usuario;

import lombok.Data;

@Data
public  class RequestUsuarioCadastrarDTO {
    public String email;
    public String senha;
    public String situacao;

    }

