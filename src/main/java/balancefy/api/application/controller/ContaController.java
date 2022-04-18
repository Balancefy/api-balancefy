package balancefy.api.application.controller;

import balancefy.api.application.config.security.TokenService;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.application.dto.response.ContaResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.domain.services.ContaService;
import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<ContaResponseDto> findById(@RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));
            ContaResponseDto account = new ContaResponseDto(contaService.getContaById(id));
            return ResponseEntity.status(200).body(account);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ContaResponseDto(ex));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ContaResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ContaResponseDto(ex));
        }
    }

    @PostMapping
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

    @PutMapping
    public ResponseEntity<ContaResponseDto> update(@RequestHeader(value = "Authorization") String token, @RequestBody Conta conta) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));
            conta.setId(id);
            ContaResponseDto updatedAccount = new ContaResponseDto(contaService.update(conta));
            return ResponseEntity.status(200).body(updatedAccount);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ContaResponseDto(ex));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ContaResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ContaResponseDto(ex));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            contaService.delete(id);
            return ResponseEntity.status(200).build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}/{progValue}")
    public ResponseEntity updateProgress(@PathVariable Integer id,
            @PathVariable Double progValue){
        try {
            contaService.updateProgress(id ,progValue);
            return ResponseEntity.status(200).build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
}
