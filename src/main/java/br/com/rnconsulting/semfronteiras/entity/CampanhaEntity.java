package br.com.rnconsulting.semfronteiras.entity;

import java.util.Date;

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
@Table(name = "tb_campanha")
public class CampanhaEntity {
    
    @Id
    private int id;
    private String descricao;
    private Date dtInicial;
    private Date dtEncerramento;
    private String cpfPessoaCadastrante;
    private Double previsaoArrecadacaoFinanceira;
    private Double arrecadadoFinanTotal;
    private int previsaoArrecadacaoAlimentacao;
    private Double arrecadacaoAliTotal;

}
