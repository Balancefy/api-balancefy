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
    public ResponseEntity<TopicoResponseDto> create(@RequestBody TopicoResponseDto topico,
                                                    @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));
            topico.setId(id);
            TopicoResponseDto account = new TopicoResponseDto(topicoService.create(topico));
            return ResponseEntity.status(201).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new TopicoResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new TopicoResponseDto(ex));
        }
    }
}
