package br.com.rnconsulting.semfronteiras.controller;

import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import br.com.rnconsulting.semfronteiras.repositories.UsuarioRepository;
import br.com.rnconsulting.semfronteiras.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;

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

    @PostMapping
    @Operation(summary = "Cadastrar Novo Usuário", description = "")
    @ApiResponse(responseCode = "201", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation =  UsuarioEntity.class)))
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioEntity usuarioEntity) {

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
