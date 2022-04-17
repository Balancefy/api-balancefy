package balancefy.api.application.controller;

import balancefy.api.application.dto.request.LoginDto;
import balancefy.api.application.dto.response.ContaResponseDto;
import balancefy.api.application.dto.response.LoginResponseDto;
import balancefy.api.application.dto.response.UsuarioResponseDto;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.domain.services.ContaService;
import balancefy.api.domain.services.UsuarioService;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping
public class AutenticacaoController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ContaService contaService;

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginDto) {
//        try {
//            UsuarioResponseDto account = new UsuarioResponseDto(usuarioService.create(usuario));
//            return ResponseEntity.status(201).body(account);
//        } catch (AlreadyExistsException ex) {
//            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
//        } catch (HttpServerErrorException.InternalServerError ex) {
//            return ResponseEntity.status(500).body(new UsuarioResponseDto(ex));
//        } catch (Exception ex) {
//            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody @Valid Usuario usuario) {
        try {
            UsuarioResponseDto account = new UsuarioResponseDto(usuarioService.create(usuario));
            return ResponseEntity.status(201).body(account);
        } catch (AlreadyExistsException ex) {
            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new UsuarioResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
        }
    }

    @PostMapping("/register/account")
    public ResponseEntity<ContaResponseDto> create(@RequestBody @Valid Conta conta) {
        try {
            ContaResponseDto createdAccount = new ContaResponseDto(contaService.create(conta));
            return ResponseEntity.status(201).body(createdAccount);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ContaResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ContaResponseDto(ex));
        }
    }
}
