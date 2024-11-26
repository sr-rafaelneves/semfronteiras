package br.com.rnconsulting.semfronteiras.controller;

import br.com.rnconsulting.semfronteiras.dto.usuario.RequestUsuarioCadastrarDTO;
import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import br.com.rnconsulting.semfronteiras.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
@ResponseBody
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Consulta de Todos os Usuários", description = "Realiza uma consulta de todos os usuários existentes.")
    @ApiResponse(responseCode = "201", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation =  UsuarioEntity.class))))
    public ResponseEntity<?> listarUsuarios() {

        return usuarioService.listarUsuarios();
    }

    @GetMapping("cpfcnpj/{cpfcnpj}")
    @Operation(summary = "Consultar Usuário pelo CPFCNPJ", description = "")
    @ApiResponse(responseCode = "201", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation =  UsuarioEntity.class)))
    public ResponseEntity<?> pesqUsuarioCpfcnpj(@PathVariable String cpfcnpj) {


        return usuarioService.pesqUsuarioCpfcnpj(cpfcnpj);
    }

    @GetMapping("email/{email}")
    @Operation(summary = "Consultar Usuário pelo Email ", description = "")
    @ApiResponse(responseCode = "201", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation =  UsuarioEntity.class)))
    public ResponseEntity<?> listarUsuarios(@PathVariable String email) {

        return usuarioService.pesqUsuarioId(email);
    }

    @PostMapping("/{cpfcnpj}")
    @Operation(summary = "Cadastrar Novo Usuário", description = """
            Informar CPFCPNJ da Pessoa na URL (Obrigatório) \n
            Campo "email" (Obrigatório) \n
            Campo "Senha" com no minimo 4 caracteres (Obrigatório) \n
            Campo "situacao" Informar "A" = Ativo | "I" = Inativo | Default = "A" (Opcional) \n
            """)
    @ApiResponse(responseCode = "201", description = "Sucesso")
    public ResponseEntity<?> criarUsuario(@PathVariable String cpfcnpj, @RequestBody RequestUsuarioCadastrarDTO usuarioRequest) {

            UsuarioEntity usuarioEntity = new UsuarioEntity();

            usuarioEntity.setEmail(usuarioRequest.getEmail());
            usuarioEntity.setSenha(usuarioRequest.getSenha());
            usuarioEntity.setSituacao(usuarioRequest.getSituacao());

            if(cpfcnpj != null) {
                usuarioEntity.getPessoaEntity().setCpfcnpj(cpfcnpj);
            }
        return usuarioService.criarUsuario(usuarioEntity);
    }


    @PutMapping
    @Operation(summary = "Atualizar Usuário", description = "")
    @ApiResponse(responseCode = "201", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation =  UsuarioEntity.class)))
    public UsuarioEntity atualizarUsuario(@RequestBody UsuarioEntity usuarioEntity) {

        return usuarioService.atualizarUsuario(usuarioEntity);
    }

}
