package balancefy.api.application.controller;

import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.application.dto.response.ContaResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.domain.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/accounts")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<ContaResponseDto> findById() {
        try {
            Integer id = 1;
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

    @PutMapping
    public ResponseEntity<ContaResponseDto> update(@RequestBody Conta conta) {
        try {
            // precisa verificar o token
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
