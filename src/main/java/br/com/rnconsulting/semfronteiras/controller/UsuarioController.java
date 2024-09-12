package br.com.rnconsulting.semfronteiras.controller;

import br.com.rnconsulting.semfronteiras.entity.UsuarioEntity;
import br.com.rnconsulting.semfronteiras.repositories.UsuarioRepository;
import br.com.rnconsulting.semfronteiras.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioEntity> listarUsuarios(){

    return usuarioService.listarUsuarios();
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario (@RequestBody UsuarioEntity usuarioEntity){

    return usuarioService.criarUsuario(usuarioEntity);
    }

    @PutMapping
    public UsuarioEntity atualizarUsuario (@RequestBody UsuarioEntity usuarioEntity){

        return usuarioService.atualizarUsuario(usuarioEntity);
    }

}
