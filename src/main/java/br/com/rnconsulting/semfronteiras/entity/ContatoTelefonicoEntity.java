package br.com.rnconsulting.semfronteiras.entity;

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
@Table(name = "tb_telefone")
public class ContatoTelefonicoEntity {
    
    @Id
    private String cpfPessoa;
    @Nonnull
    private String numCelular;
    @Nonnull
    private String numWhatsApp;
}
