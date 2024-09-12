package br.com.rnconsulting.semfronteiras.repositories;

import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findBycpfcnpj(String cpfcnpj);
}
