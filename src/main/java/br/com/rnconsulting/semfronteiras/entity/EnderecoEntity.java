package br.com.rnconsulting.semfronteiras.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_endereco")
public class EnderecoEntity {
    
    @Id
    private String cpfpessoa;
    private String cep;
    private String endereco;
    private String numEndereco;
    private String complemento;
    private String bairro;
    private String municipio;
    private String uf;
}
