package br.com.rnconsulting.semfronteiras.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tb_pessoa")
public class PessoaEntity {
    
    @Id
    @Column(length = 14, unique = true)
    private String cpfcnpj;
    private String numRg;
    private String ufRg;
    private String nomeCompleto;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dtNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String tituloEleitor;
    private String numPassaporte;
    private String federacaoPassaporte;
    private String statusPessoa;
    private String tipoPessoa;

}
