package br.com.rnconsulting.semfronteiras.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tb_usuario")
public class UsuarioEntity {

    @Id
    private String email;

    @NonNull
    private String senha;

    @Column(unique = true)
    @NonNull
    private String cpfcnpj;

    @NonNull
    private String situacao;

   /* @OneToOne
    @JoinColumn(name = "cpfcnpj")
    private PessoaEntity pessoaEntity;
    */

}
