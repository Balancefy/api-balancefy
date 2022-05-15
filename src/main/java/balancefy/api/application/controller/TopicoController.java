package balancefy.api.application.controller;

import balancefy.api.application.config.security.TokenService;
import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.application.dto.response.TopicoResponseDto;
import balancefy.api.domain.services.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/forum")
public class TopicoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoResponseDto> create(@RequestBody TopicoRequestDto topico,
                                                    @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));
            TopicoResponseDto account = new TopicoResponseDto(topicoService.create(topico, id));
            return ResponseEntity.status(201).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new TopicoResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new TopicoResponseDto(ex));
        }
    }

    @PatchMapping
    public ResponseEntity<TopicoResponseDto> update(@RequestBody TopicoRequestDto topico,
                                                    @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));
            TopicoResponseDto account = new TopicoResponseDto(topicoService.update(topico, id));
            return ResponseEntity.status(200).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new TopicoResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new TopicoResponseDto(ex));
        }
    }
}
