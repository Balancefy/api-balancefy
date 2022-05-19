package balancefy.api.application.controller;

import balancefy.api.application.dto.request.ObjetivoDto;

import balancefy.api.application.dto.response.ObjetivoResponseDto;
import balancefy.api.domain.services.ObjetivoService;
import balancefy.api.resources.entities.ObjetivoConta;
import balancefy.api.resources.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import balancefy.api.application.config.security.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {

    @Autowired
    ObjetivoService objetivoService;

    @Autowired
    TokenService tokenService;


    @PostMapping
    public ResponseEntity create (@RequestHeader(value = "Authorization") String token, @RequestBody ObjetivoDto objetivo) {
      Integer userId = tokenService.getIdUsuario(token.replace("Bearer ", ""));
        try{
            return ResponseEntity.status(200).body(objetivoService.create(objetivo, userId));
        }catch (HttpServerErrorException.InternalServerError ex){
            return ResponseEntity.status(500).body("f");
        }
    }

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