package balancefy.api.application.controller;

import balancefy.api.application.dto.response.ListObjetivoResponseDto;
import balancefy.api.domain.services.ObjetivoService;
import balancefy.api.resources.entities.Objetivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goals")
public class ObjetivoController {
    @Autowired
    ObjetivoService objetivoService;

    @GetMapping
    public ResponseEntity<ListObjetivoResponseDto> listAll() {
        try{
            return ResponseEntity.ok( new ListObjetivoResponseDto("Sucesso", objetivoService.listPreMade()));
        }catch (Exception e) {
            return ResponseEntity.status(500).body(new ListObjetivoResponseDto(e.getMessage()));
        }
    }

}
