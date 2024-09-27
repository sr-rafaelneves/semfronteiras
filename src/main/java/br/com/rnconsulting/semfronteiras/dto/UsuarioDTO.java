package br.com.rnconsulting.semfronteiras.dto;

import br.com.rnconsulting.semfronteiras.entity.PessoaEntity;
import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class UsuarioDTO {
    private String email;
    private String situacao;
    private String cpfcnpj;


    private PessoaEntity pessoaEntity = new PessoaEntity();



    public UsuarioDTO(UsuarioEntity usuarioEntity){
        this.email = usuarioEntity.getEmail();
        this.situacao = usuarioEntity.getSituacao();
        this.pessoaEntity.setCpfcnpj(usuarioEntity.getPessoaEntity().getCpfcnpj());

    }
}
