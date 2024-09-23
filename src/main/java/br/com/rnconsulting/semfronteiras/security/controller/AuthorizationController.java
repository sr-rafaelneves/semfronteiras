package br.com.rnconsulting.semfronteiras.security.controller;

import br.com.rnconsulting.semfronteiras.security.entity.TokenResponseRecord;
import br.com.rnconsulting.semfronteiras.security.entity.User;
import br.com.rnconsulting.semfronteiras.security.entity.UserRecord;
import br.com.rnconsulting.semfronteiras.security.repository.UserRepository;
import br.com.rnconsulting.semfronteiras.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity login (@RequestBody @Validated UserRecord userRecord){

        // System.out.println(new BCryptPasswordEncoder().encode(userRecord.senha())); // ver a senha criptografada

    // Criptografando a senha junto com o login
    var loginSenha = new UsernamePasswordAuthenticationToken(userRecord.login(), userRecord.senha());

    var auth = this.authenticationManager.authenticate(loginSenha);


    var token = tokenService.gerarToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new TokenResponseRecord(token));
    }

    

}
