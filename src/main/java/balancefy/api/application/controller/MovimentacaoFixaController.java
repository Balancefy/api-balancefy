package balancefy.api.application.controller;

import balancefy.api.application.dto.response.MovimentacaoFixaDto;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.domain.services.MovimentacaoFixaService;
import balancefy.api.resources.entities.MovimentacaoFixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
@RestController
@RequestMapping("/transactionFixed")
public class MovimentacaoFixaController {

    @Autowired
    private MovimentacaoFixaService movimentacaoFixaService;


    @GetMapping("/{id}")
    public List<MovimentacaoFixaDto> getList(@PathVariable Integer id) {
        return movimentacaoFixaService.getAllMovimentacaoFixa(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody MovimentacaoFixa movimentacaoFixa) {
        try {
            movimentacaoFixaService.create(movimentacaoFixa);
            return ResponseEntity.status(201).build();
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            movimentacaoFixaService.deleteMovimentacaoFixa(id);
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
