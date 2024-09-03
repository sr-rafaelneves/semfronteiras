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
@Table(name = "tb_redesocial")
public class RedeSocialEntity {
    
    @Id
    private String cpfPessoa;
    @Nonnull
    private String email;
    private String instagram;
    private String facebook;
    private String linkedin;
    private String twitter;
    private String outroContato;

}
