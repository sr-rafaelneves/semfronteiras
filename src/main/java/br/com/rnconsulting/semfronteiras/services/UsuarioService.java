package br.com.rnconsulting.semfronteiras.services;

import br.com.rnconsulting.semfronteiras.Exception.CustomException;
import br.com.rnconsulting.semfronteiras.dto.usuario.RequestUsuarioCadastrarDTO;
import br.com.rnconsulting.semfronteiras.dto.usuario.ResponseUsuarioConsultarDTO;
import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import br.com.rnconsulting.semfronteiras.repositories.PessoaRepository;
import br.com.rnconsulting.semfronteiras.repositories.UsuarioRepository;
import org.apache.coyote.Response;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Search all users
     *
     * @return List<ResponseUsuarioConsultarDTO>
     */
    public ResponseEntity<?> listAllUsuarios() {

        List<UsuarioEntity> listUsuarios = usuarioRepository.findAll();
        List<ResponseUsuarioConsultarDTO> listRetorno = new ArrayList<>();


        if (listUsuarios.size() >= 1) { // SE TIVER USUARIO NA LISTA
            for (UsuarioEntity u : listUsuarios) { //PERCORRE A LISTA E ADICIONA NA LISTAGEM DE DTO PARA RETORNO

                ResponseUsuarioConsultarDTO ret = new ResponseUsuarioConsultarDTO(u.getId(), u.getEmail(), u.getSituacao(), u.getPessoaEntity().getCpfcnpj());
                listRetorno.add(ret);

            }
        }


        return ResponseEntity.status(HttpStatus.OK).body(listRetorno);
    }

    /**
     * Search users by Email
     *
     * @param email
     * @return ResponseUsuarioConsultarDTO
     */
    public ResponseEntity<?> SearchUsuarioEmail(String email) {

        if (email == null || !email.contains("@")) {
            throw new CustomException("Informe um Email valido");
        }

        UsuarioEntity user = usuarioRepository.findByEmail(email);

        ResponseUsuarioConsultarDTO response = null;

        if (user != null) {
            response = new ResponseUsuarioConsultarDTO(user.getId(), user.getEmail(),
                    user.getSituacao(),
                    user.getPessoaEntity().getCpfcnpj());

        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Search users by CPFCNPJ
     *
     * @param cpfcnpj
     * @return ResponseUsuarioConsultarDTO
     */
    public ResponseEntity<?> SearchUsuarioCpfcnpj(String cpfcnpj) {

        if (cpfcnpj == null) {
            throw new CustomException("CPF ou CNPJ Não Informado");

        } else if (cpfcnpj.length() < 11 || cpfcnpj.length() > 14) {

            throw new CustomException("CPF ou CNPJ Invalido");
        }


        UsuarioEntity usuarioEntity = usuarioRepository.findByPessoaEntityCpfcnpj(cpfcnpj);

        ResponseUsuarioConsultarDTO response = null;

        if (usuarioEntity != null) {
            response = new ResponseUsuarioConsultarDTO(usuarioEntity.getId(), usuarioEntity.getEmail(), usuarioEntity.getSituacao(), usuarioEntity.getPessoaEntity().getCpfcnpj());

        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Create user
     *
     * @param cpfcnpj
     * @param requestUsuarioCadastrarDTO
     * @return
     */
    public ResponseEntity<?> createUsuario(String cpfcnpj, RequestUsuarioCadastrarDTO requestUsuarioCadastrarDTO) {

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setEmail(requestUsuarioCadastrarDTO.getEmail());
        usuarioEntity.setSenha(requestUsuarioCadastrarDTO.getSenha());

        if (requestUsuarioCadastrarDTO.getSituacao() != null) {

            usuarioEntity.setSituacao(requestUsuarioCadastrarDTO.getSituacao());
        }

        if (cpfcnpj != null) {
            usuarioEntity.getPessoaEntity().setCpfcnpj(cpfcnpj);
        }

        // FIM AJUSTE DO DTO

        validarCampos(usuarioEntity); // REALIZA A VALIDAÇÃO DOS CAMPOS BASE DO USUARIO

        // VERIFICA SE O EMAIL JÁ ESTÁ CADASTRO NO BANCO DE DADOS
        if (usuarioRepository.findByEmail(usuarioEntity.getEmail()) != null) {

            throw new CustomException("E-mail já cadastrado");

        }

        if(usuarioEntity.getEmail() != null && !usuarioEntity.getEmail().contains("@")){
            throw new CustomException("Informe um Email valido");
        }

        //VERIFICA SE PESSOAENTITY NÃO FOI INFORMADO
        if (usuarioEntity.getPessoaEntity() == null) {
            throw new CustomException("PessoaEntity Não informado");
        }

        //VERIFICA SE O CPFCNPJ NÃO FOI INFORMADO
        if (usuarioEntity.getPessoaEntity().getCpfcnpj() == null || usuarioEntity.getPessoaEntity().getCpfcnpj().isEmpty()) {

            throw new CustomException("O campo CPFCNPJ não pode ser Nulo ou em Branco");

        }

        //VALIDA SE EXISTE UMA PESSOA CADASTRADA COM O CPFCNPJ INFORMADO
        if (pessoaRepository.findById(usuarioEntity.getPessoaEntity().getCpfcnpj()).isEmpty()) {
            throw new CustomException("Não Existe Pessoa Cadastrada com CPFCNPJ Informado");

        }
//        //Inicio do Bloco VERIFICA SE JÁ EXISTE USUARIO CADASTRADO COM O CPF INFOMRADO SE EXISTIR DA EXCEPTION SE NÃO SE FOR CNPJ PERMITE
        List<UsuarioEntity> usuarios = usuarioRepository.findAllByPessoaEntityCpfcnpj(usuarioEntity.getPessoaEntity().getCpfcnpj());

        for (UsuarioEntity user : usuarios) {
            if (user.getPessoaEntity().getCpfcnpj().length() == 11 && user.getPessoaEntity().getCpfcnpj().equalsIgnoreCase(usuarioEntity.getPessoaEntity().getCpfcnpj())) {
                throw new CustomException("O CPF já está vinculado a um Usuário");
            }
        }
        // fim bloco validar CPF e CNPJ


        // SE A SITUAÇÃO DO USUARIO NÃO FOR PASSADA SERÁ POR PADRÃO CRIADO ATIVO
        if (usuarioEntity.getSituacao() == null || usuarioEntity.getSituacao().isEmpty()) {

            usuarioEntity.setSituacao("A");

        } else if (usuarioEntity.getSituacao() != null && usuarioEntity.getSituacao().length() > 1) {

            throw new CustomException("Atributo situacao deve informar 'A' para Ativo ou 'I' para Inativo");

        } else if (!usuarioEntity.getSituacao().equalsIgnoreCase("I") && !usuarioEntity.getSituacao().equalsIgnoreCase("A")) {

            throw new CustomException("Atributo situacao deve informar 'A' para Ativo ou 'I' para Inativo");
        }


        usuarioEntity.setSenha(passwordEncoder.encode(usuarioEntity.getSenha())); // CRIPTOGRAFA A SENHA

        usuarioRepository.save(usuarioEntity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> updateUsuario(String emailAtual, RequestUsuarioCadastrarDTO requestUsuarioCadastrarDTO) {


        UsuarioEntity usuarioEntity = new UsuarioEntity(); // usuario para receber os dados do dto

        if (requestUsuarioCadastrarDTO.getEmail() != null && !requestUsuarioCadastrarDTO.getEmail().equals("")) {

            usuarioEntity.setEmail(requestUsuarioCadastrarDTO.getEmail());

        }
        if (requestUsuarioCadastrarDTO.getSenha() != null && !requestUsuarioCadastrarDTO.getSenha().equals("")) {
            usuarioEntity.setSenha(requestUsuarioCadastrarDTO.getSenha()); // CRIPTOGRAFA A SENHA

        }
        if (requestUsuarioCadastrarDTO.getSituacao() != null && !requestUsuarioCadastrarDTO.getSituacao().equals("")) {

            usuarioEntity.setSituacao(requestUsuarioCadastrarDTO.getSituacao());

        }
        // FIM AJUSTE DE DTO


        if (emailAtual == null || !emailAtual.contains("@")) {
            throw new CustomException("Informe um Email valido");
        }

        if (usuarioEntity.getEmail() != null && !usuarioEntity.getEmail().contains("@")) {
            throw new CustomException("Informe um novo Email valido");
        }

        if (usuarioEntity.getSituacao() != null && usuarioEntity.getSituacao().length() >= 1) {

            if (!usuarioEntity.getSituacao().equalsIgnoreCase("I") && !usuarioEntity.getSituacao().equalsIgnoreCase("A")) {

                throw new CustomException("Atributo situacao deve informar 'A' para Ativo ou 'I' para Inativo");
            }

        }
        if (usuarioEntity.getSenha() != null && usuarioEntity.getSenha().length() < 4) {

            throw new CustomException("A Senha deve conter no minimo 4 caracteres");

        }

        UsuarioEntity usuarioRetornoConsulta = usuarioRepository.findByEmail(emailAtual);

        if (usuarioRetornoConsulta == null) {
            throw new CustomException("Não foi Encontrado Usuário");
        } else {
            usuarioEntity.getPessoaEntity().setCpfcnpj(usuarioRetornoConsulta.getPessoaEntity().getCpfcnpj()); // REPASSA O CPFCNPJ PARA O OBJETO USUARIO VINDO DO BODY DO REQUEST
            usuarioEntity.setId(usuarioRetornoConsulta.getId());
        }

        if (usuarioEntity.getEmail() == null) { //SE NÃO FOR PASSANDO UM NOVO EMAIL O MESMO EMAIL SERA ENVIADO
            usuarioEntity.setEmail(emailAtual);
        }
        if (usuarioEntity.getSituacao() == null) { // REPASSE DE DADOS
            usuarioEntity.setSituacao(usuarioRetornoConsulta.getSituacao());
        }
        if (usuarioEntity.getSenha() == null) { //REPASSE DE DADOS
            usuarioEntity.setSenha(usuarioRetornoConsulta.getSenha());
        }

        usuarioRepository.save(usuarioEntity);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Metodo para Validar os Campos do Usuário
     *
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
