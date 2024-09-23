package br.com.rnconsulting.semfronteiras.controller;

import br.com.rnconsulting.semfronteiras.entity.EnderecoEntity;
import br.com.rnconsulting.semfronteiras.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/{cpfcnpj}")
    public List<EnderecoEntity> findById(@PathVariable String cpfcnpj){
        Optional<EnderecoEntity> retorno = enderecoRepository.findById(cpfcnpj);
        return List.of();
    }



}
