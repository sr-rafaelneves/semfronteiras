package br.com.rnconsulting.semfronteiras.controller;

import br.com.rnconsulting.semfronteiras.dto.usuario.RequestUsuarioCadastrarDTO;
import br.com.rnconsulting.semfronteiras.dto.usuario.ResponseUsuarioConsultarDTO;
import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import br.com.rnconsulting.semfronteiras.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
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
    @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ResponseUsuarioConsultarDTO.class))))
    public ResponseEntity<?> listAllUsuarios() {

        return usuarioService.listAllUsuarios();
    }

    @GetMapping("/cpfcnpj/{cpfcnpj}")
    @Operation(summary = "Consultar Usuário pelo CPFCNPJ", description = "")
    @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseUsuarioConsultarDTO.class)))
    public ResponseEntity<?> SearchUsuarioCpfcnpj(@PathVariable String cpfcnpj) {


        return usuarioService.SearchUsuarioCpfcnpj(cpfcnpj);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Consultar Usuário pelo Email ", description = "")
    @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseUsuarioConsultarDTO.class)))
    public ResponseEntity<?> searchById(@PathVariable String email) {


        return usuarioService.SearchUsuarioEmail(email);
    }

    @PostMapping("/{cpfcnpj}")
    @Operation(summary = "Cadastrar Novo Usuário", description = """
            Informar CPFCPNJ da Pessoa na URL (Obrigatório) \n
            Campo "email" (Obrigatório) \n
            Campo "Senha" com no minimo 4 caracteres (Obrigatório) \n
            Campo "situacao" Informar "A" = Ativo | "I" = Inativo | Default = "A" (Opcional) \n
            """)
    @ApiResponse(responseCode = "201", description = "Sucesso")
    public ResponseEntity<?> createUsuario(@PathVariable String cpfcnpj, @RequestBody RequestUsuarioCadastrarDTO usuarioRequest) {


        return usuarioService.createUsuario(cpfcnpj, usuarioRequest);
    }


    @PutMapping("/{email}")
    @Operation(summary = "Atualizar Usuário", description = "")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<?> updateUsuario(@PathVariable String email, @RequestBody RequestUsuarioCadastrarDTO requestUsuarioCadastrarDTO) {


        return usuarioService.updateUsuario(email, requestUsuarioCadastrarDTO);
    }

}
