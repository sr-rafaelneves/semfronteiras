package br.com.rnconsulting.semfronteiras.entity;

import java.util.Date;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_doacao")
public class DoacaoEntity {
    
    @Id
    private int id;
    @Nonnull
    private String descricao;
    @Nonnull
    private Double qtd;
    @Nonnull
    private int idTipoDoacao;
    @Nonnull
    private Double ajudaFinanceira;
    @Nonnull
    private Date dt;
    @Nonnull
    private String cpfPessoaCadastrante;
    @Nonnull
    private String cpfPessoaDoadora;
    @Nonnull
    private int idCampanha;

}
