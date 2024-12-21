package br.com.rnconsulting.semfronteiras.repositories;

import br.com.rnconsulting.semfronteiras.entity.PessoaEntity;
import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    UsuarioEntity findByPessoaEntityCpfcnpj(String cpfcnpj);
    UsuarioEntity findByEmail(String email);
    List<UsuarioEntity> findAllByPessoaEntityCpfcnpj(String cpfcnpj);
}
