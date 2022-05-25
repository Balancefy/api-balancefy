package balancefy.api.application.controller;

import balancefy.api.application.dto.request.ObjetivoDto;

import balancefy.api.application.dto.response.ObjetivoContaResponseDto;
import balancefy.api.application.dto.response.ObjetivoResponseDto;
import balancefy.api.application.dto.response.ReachOutDto;
import balancefy.api.domain.exceptions.AmountException;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.domain.services.ObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import balancefy.api.application.config.security.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import java.util.List;

import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/accounts/goals")
public class ObjetivoContaController {

    @Autowired
    ObjetivoService objetivoService;

    @Autowired
    TokenService tokenService;


    @PostMapping
    public ResponseEntity create(@RequestHeader(value = "Authorization") String token, @RequestBody ObjetivoDto objetivo) {
        Integer userId = tokenService.getIdUsuario(token.replace("Bearer ", ""));
        try {
            return ResponseEntity.status(200).body(objetivoService.create(objetivo, userId));
        } catch (HttpServerErrorException.InternalServerError | DataFormatException ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (AmountException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping ResponseEntity<List<ObjetivoContaResponseDto>> getList(@RequestHeader(value = "Authorization") String token){
        Integer accountId = tokenService.getIdUsuario(token.replace("Bearer", ""));
        return ResponseEntity.status(200).body(objetivoService.getList(accountId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivoResponseDto> findById(@PathVariable Integer id) {
        try {
            ObjetivoResponseDto objetivo = objetivoService.getObjetivoById(id);
            return ResponseEntity.status(200).body(objetivo);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ObjetivoResponseDto(ex));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ObjetivoResponseDto(ex));
        }
    }

    @GetMapping("/{id}/reachout")
    public ResponseEntity<ReachOutDto> reachOut(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(200).body(new ReachOutDto("Sucesso", objetivoService.reachOutCurrentGoal(id)));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ReachOutDto(e.getMessage()));
        }
    }


    //TODO completar task
    //Listar objetivos da conta


}