package balancefy.api.application.controller;

import balancefy.api.application.config.security.TokenService;
import balancefy.api.application.dto.response.MovimentacaoResponseDto;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.domain.services.MovimentacaoService;
import balancefy.api.resources.entities.Movimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/{id}")
    public List<Movimentacao> getList(@PathVariable Integer id) {
        return movimentacaoService.getAllMovimentacao(id);
    }

    @GetMapping({"/goal/{id}"})
    public List<MovimentacaoResponseDto> getListByGoal(@PathVariable Integer id) {
        return movimentacaoService.getAllByObjetivo(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Movimentacao movimentacao) {
        try {
            movimentacaoService.create(movimentacao);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            movimentacaoService.deleteMovimentacao(id);
            return ResponseEntity.status(200).build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping("/report")
    public ResponseEntity exportCsv(@RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));

            movimentacaoService.exportCsv(id);

            File file = new File("movimentacoes");
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity
                    .status(200)
                    .header("content-type", "text/plain")
                    .header("content-disposition", "filename=\"movimentacoes.txt\"")
                    .body(resource);
        } catch (IOException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
