package br.com.rnconsulting.semfronteiras.controller;

import br.com.rnconsulting.semfronteiras.Exception.CustomException;
import br.com.rnconsulting.semfronteiras.services.PessoaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.rnconsulting.semfronteiras.entity.PessoaEntity;

import java.util.List;

@Tag(name = "Pessoa")
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value = "/checkservice")
    public String checkService(){
        return "Service Status OK!";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cpf}")
    public PessoaEntity findById(@PathVariable String cpf){
        PessoaEntity pessoa = new PessoaEntity();

        pessoa.setCpfcnpj(cpf);

        PessoaEntity retorno = pessoaService.SearchID(pessoa);
        return retorno;
    }

    @GetMapping
    public List<PessoaEntity> findAll(){
        return pessoaService.SearchALL();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201",description = "CREATED")
    public PessoaEntity Insert(@RequestBody PessoaEntity pessoaEntity){

        if (pessoaService.isAutentic(pessoaEntity.getCpfcnpj())){
            throw new CustomException("Usuário já existe");
        }

        return pessoaService.Save(pessoaEntity);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200",description = "OK")
    public String Modify(@RequestBody PessoaEntity pessoaEntity){

        String retorno = "";

        if (pessoaService.isAutentic(pessoaEntity.getCpfcnpj())){

            pessoaService.Save(pessoaEntity);

            retorno = "Registro editado com sucesso!";

        } else {
            throw new CustomException("Usuário não registrado.");
        }

        return retorno;
    }


}