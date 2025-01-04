package br.com.rnconsulting.semfronteiras.services;

import br.com.rnconsulting.semfronteiras.Exception.CustomException;
import br.com.rnconsulting.semfronteiras.entity.PessoaEntity;
import br.com.rnconsulting.semfronteiras.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public boolean isAutentic (String cpfcnpj) {
        return pessoaRepository.findByCpfcnpj (cpfcnpj).isPresent();
    }

    public PessoaEntity SearchID(PessoaEntity pessoaEntity){

        if(pessoaEntity.getCpfcnpj().length() == 0){
            throw new CustomException("CPF ou CNPJ não pode ser pesquisado.");
        } else if (pessoaEntity.getCpfcnpj().length() < 11 || pessoaEntity.getCpfcnpj().length() > 14) {
            throw new CustomException("CPF ou CNPJ inválido, a quantidade de digitos não é correta.");
        }else if (pessoaEntity.getCpfcnpj().length() > 11 && pessoaEntity.getCpfcnpj().length() < 14) {
            throw  new CustomException("CPF ou CNPJ inválido, a quantidade de digitos não é correta.");
        }

        return pessoaRepository.findByCpfcnpj(pessoaEntity.getCpfcnpj()).get();

    }

    public List<PessoaEntity> SearchALL(){

        return pessoaRepository.findAll();
    }

    public PessoaEntity Save(PessoaEntity pessoaEntity){

        if (pessoaEntity.getCpfcnpj().length() == 0){
            throw  new CustomException("Impossível registrar, insira o CPF ou CNPJ");
        }else if(pessoaEntity.getCpfcnpj().length() < 11 && pessoaEntity.getCpfcnpj().length() > 14 ){
            throw  new CustomException("Impossível pesquisar, CPF ou CNPJ inválido, informe apenas os números!");
        } else if (pessoaEntity.getCpfcnpj().length() > 11 && pessoaEntity.getCpfcnpj().length() < 14) {
            throw  new CustomException("Impossível pesquisar, CPF ou CNPJ inválido, informe apenas os números!");
        } else if(pessoaEntity.getNomeCompleto().length() < 3 ){
            throw new CustomException("Impossível registrar, a quantidade de letras é inválida para formulação de nome prório.");
        }

        return pessoaRepository.save(pessoaEntity);
    }



}
