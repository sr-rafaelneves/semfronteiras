package br.com.rnconsulting.semfronteiras.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_pessoa")
public class PessoaEntity {
    
    @Id
    @Column(length = 14, unique = true)
    private String cpf;
    private String numRg;
    private String ufRg;
    private String nomeCompleto;
    private int anoNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String tituloEleitor;
    private String numPassaporte;
    private String federacaoPassaporte;
    private int statusPessoa;
    private String tipoPessoa;


}
