package br.com.rnconsulting.semfronteiras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.rnconsulting.semfronteiras.entity.PessoaEntity;
import br.com.rnconsulting.semfronteiras.repositories.PessoaRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value = "/checkservice")
    public String checkService(){
        return "Service Status OK!";
    }

    @GetMapping("/{cpf}")
    public PessoaEntity findById(@PathVariable String cpf){
        PessoaEntity retorno = pessoaRepository.findById(cpf).get();
        return retorno;
    }

    @GetMapping
    public List<PessoaEntity> All(){
        List<PessoaEntity> retorno = pessoaRepository.findAll();
        return retorno;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaEntity insert(@RequestBody PessoaEntity pessoaEntity){
        PessoaEntity retorno = pessoaRepository.save(pessoaEntity);
        return retorno;
    }


}