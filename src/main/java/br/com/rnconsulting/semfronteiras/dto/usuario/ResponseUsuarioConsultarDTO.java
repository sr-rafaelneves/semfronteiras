package br.com.rnconsulting.semfronteiras.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUsuarioConsultarDTO {

    private UUID id;
    private String email;
    private String situacao;
    private String cpfcnpj;

}
