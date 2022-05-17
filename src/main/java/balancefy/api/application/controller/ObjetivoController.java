package balancefy.api.application.controller;

import balancefy.api.application.dto.response.ObjetivoContaResponseDto;
import balancefy.api.application.dto.response.ObjetivoResponseDto;
import balancefy.api.domain.services.ObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {

    @Autowired
    ObjetivoService objetivoService;

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivoResponseDto> getById(@PathVariable Integer id) {
        try {
           ObjetivoResponseDto objetivo = objetivoService.getObjetivoById(id);
           return ResponseEntity.status(200).body(objetivo);
        }catch(HttpServerErrorException.InternalServerError ex){
            return ResponseEntity.status(500).body(new ObjetivoResponseDto(ex));
        }
    }
}