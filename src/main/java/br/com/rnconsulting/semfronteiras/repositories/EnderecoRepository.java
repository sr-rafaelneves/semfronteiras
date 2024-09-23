package br.com.rnconsulting.semfronteiras.repositories;

import br.com.rnconsulting.semfronteiras.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository  extends JpaRepository<EnderecoEntity,String> {
//    Optional<EnderecoEntity> findByEnderecoEntityCpfcnpj(String link_cpfcnpj);
}
