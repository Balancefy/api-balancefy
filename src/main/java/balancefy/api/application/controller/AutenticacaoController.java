package balancefy.api.application.controller;

import balancefy.api.application.config.security.AutenticacaoService;
import balancefy.api.application.config.security.TokenService;
import balancefy.api.application.dto.request.LoginDto;
import balancefy.api.application.dto.request.LoginSocialDto;
import balancefy.api.application.dto.response.LoginResponseDto;
import balancefy.api.domain.services.ContaService;
import balancefy.api.resources.entities.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AutenticacaoService authService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> autenticar(@RequestBody LoginDto login) {
        try {
            UsernamePasswordAuthenticationToken dados = login.converter();

            Authentication authentication = authManager.authenticate(dados);
            String token = tokenService.gerarToken(authentication);

            Conta conta = contaService.getContaById(tokenService.getIdUsuario(token));

            return ResponseEntity.ok(new LoginResponseDto(conta, token));

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new LoginResponseDto(ex));
        }

    }

    @PostMapping("/social")
    public ResponseEntity<LoginResponseDto> autenticarSocial(@RequestBody LoginSocialDto login) {
        try {
            String token = tokenService.gerarTokenRedeSocial(authService.loadUserByUsername(login.getEmail()));

            Conta conta = contaService.getContaById(tokenService.getIdUsuario(token));

            return ResponseEntity.ok(new LoginResponseDto(conta, token));

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new LoginResponseDto(ex));
        }

    }


}
