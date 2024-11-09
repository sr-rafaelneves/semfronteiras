package br.com.rnconsulting.semfronteiras.services;

import br.com.rnconsulting.semfronteiras.Exception.CustomException;
import br.com.rnconsulting.semfronteiras.dto.UsuarioDTO;
import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import br.com.rnconsulting.semfronteiras.repositories.PessoaRepository;
import br.com.rnconsulting.semfronteiras.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> listarUsuarios() {

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
        }

    public ResponseEntity<?> pesqUsuarioId(String email){

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findById(email));
    }

    public ResponseEntity<?> pesqUsuarioCpfcnpj(String cpfcnpj){

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findByPessoaEntityCpfcnpj(cpfcnpj));
    }


    public ResponseEntity<?> criarUsuario(UsuarioEntity usuarioEntity) {

        validarCampos(usuarioEntity); // REALIZA A VALIDAÇÃO DOS CAMPOS BASE DO USUARIO

        // VERIFICA SE O EMAIL JÁ ESTÁ CADASTRO NO BANCO DE DADOS
        if (usuarioRepository.findById(usuarioEntity.getEmail()).isPresent()) {

            throw new CustomException("E-mail já cadastrado");

        }
        //VERIFICA SE PESSOAENTITY NÃO FOI INFORMADO
        if(usuarioEntity.getPessoaEntity() == null){
            throw new CustomException("PessoaEntity Não informado");
        }

        //VERIFICA SE O CPFCNPJ NÃO FOI INFORMADO
        if (usuarioEntity.getPessoaEntity().getCpfcnpj() == null || usuarioEntity.getPessoaEntity().getCpfcnpj().isEmpty()) {

            throw new CustomException("O campo CPFCNPJ não pode ser Nulo ou em Branco");

        }

        //VALIDA SE EXISTE UMA PESSOA CADASTRADA COM O CPFCNPJ INFORMADO
        if(pessoaRepository.findById(usuarioEntity.getPessoaEntity().getCpfcnpj()).isEmpty()){
            throw new CustomException("Não Existe Pessoa Cadastrada com CPFCNPJ Informado");

        }

        //VERIFICA SE JÁ EXISTE USUARIO CADASTRADO COM O CPFCNPJ INFOMRADO
        if(usuarioRepository.findByPessoaEntityCpfcnpj(usuarioEntity.getPessoaEntity().getCpfcnpj()).isPresent()){
            throw  new CustomException("O CPFCNPJ já está vinculado a um Usuário");
        }

        // SE A SITUAÇÃO DO USUARIO NÃO FOR PASSADA SERÁ POR PADRÃO CRIADO ATIVO
        if (usuarioEntity.getSituacao() == null || usuarioEntity.getSituacao().isEmpty()) {

            usuarioEntity.setSituacao("A");

        }

        usuarioEntity.setSenha(passwordEncoder.encode(usuarioEntity.getSenha())); // CRIPTOGRAFA A SENHA

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioRepository.save(usuarioEntity)); // DTO

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    public UsuarioEntity atualizarUsuario(UsuarioEntity usuarioEntity) {



        //VALIDA SE EXISTE UMA PESSOA CADASTRADA COM O CPFCNPJ INFORMADO
        if(pessoaRepository.findById(usuarioEntity.getPessoaEntity().getCpfcnpj()).isEmpty()){
            throw new CustomException("Não Existe Pessoa Cadastrada com CPFCNPJ Informado");

        }

        if (!usuarioRepository.findById(usuarioEntity.getEmail()).isPresent()) {
            throw new CustomException("Não foi Encontrado Usuário com o Email informado");
        }

        return usuarioRepository.save(usuarioEntity);
    }

    /**
     * Metodo para Validar os Campos do Usuário
     * @param usuarioEntity Usuário a ser validado
     * @return retorna true se passar por todas as validações
     */
    private boolean validarCampos(UsuarioEntity usuarioEntity) {

        // VALIDAR CAMPO EMAIL
        if (usuarioEntity.getEmail() == null || usuarioEntity.getEmail().isEmpty()) {

            throw new CustomException("Campo email é Obrigatório");

        }


        // VALIDAR CAMPO SENHA
        if (usuarioEntity.getSenha() == null || usuarioEntity.getSenha().isEmpty()) {

            throw new CustomException(("Uma Senha deve ser informada"));

        }

        if (usuarioEntity.getSenha().length() < 4) {

            throw new CustomException("A Senha deve conter no minimo 4 caracteres");

        }

        return true;
    }

}
