package br.com.rnconsulting.semfronteiras.repositories;

import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rnconsulting.semfronteiras.entity.PessoaEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, String>{
    Optional<PessoaEntity> findByCpfcnpj(String cpfcnpj);
}

    
