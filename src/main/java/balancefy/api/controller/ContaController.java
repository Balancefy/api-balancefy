package balancefy.api.controller;

import balancefy.api.dto.response.ContaResponseDto;
import balancefy.api.entities.Conta;
import balancefy.api.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.webjars.NotFoundException;

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
            return ResponseEntity.status(201).body(account);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ContaResponseDto(ex));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ContaResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ContaResponseDto(ex));
        }
    }

    @PostMapping
    public ResponseEntity<ContaResponseDto> create(@RequestBody Conta conta) {
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
        } catch (HttpClientErrorException.NotFound ex) {
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
        } catch (HttpClientErrorException.NotFound ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
}
