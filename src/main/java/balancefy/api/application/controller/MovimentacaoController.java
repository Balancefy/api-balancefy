package balancefy.api.application.controller;

import balancefy.api.domain.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/transaction")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping("/report")
    public ResponseEntity exportCsv() {
        try {
            movimentacaoService.exportCsv(1);

            var file = new File("movimentacoes.csv");
            var path = Paths.get(file.getAbsolutePath());
            var resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity
                    .status(200)
                    .header("content-type", "text/csv")
                    .header("content-disposition", "filename=\"movimentacoes.csv\"")
                    .body(resource);
        } catch (IOException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
