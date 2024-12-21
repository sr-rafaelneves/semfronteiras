package br.com.rnconsulting.semfronteiras.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tb_usuario")
public class UsuarioEntity {


    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;


    @Column(unique = true)
    private String email;

    @NonNull
    private String senha;


    @NonNull
    private String situacao;

    @ManyToOne
    @JoinColumn(name = "cpfcnpj")
    private PessoaEntity pessoaEntity = new PessoaEntity();


}
