package balancefy.api.application.controller;

import balancefy.api.application.config.security.TokenService;
import balancefy.api.application.dto.request.ComentarioRequestDto;
import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.application.dto.response.*;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.domain.services.ComentarioService;
import balancefy.api.domain.services.TopicoService;
import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/{id}")
    public ResponseEntity<ListaComentarioResponseDto> get(@PathVariable Integer idTopico) {
        try {
            Topico foundTopico = topicoService.getTopicoById(idTopico);
            ListaComentarioResponseDto account = new ListaComentarioResponseDto("Sucesso",comentarioService.getByFkTopico(foundTopico));
            return ResponseEntity.status(200).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ListaComentarioResponseDto(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ListaComentarioResponseDto(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ComentarioResponseDto> create(@RequestBody ComentarioRequestDto comentario,
                                                    @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));

            ComentarioDto savedComentario = comentarioService.create(comentario, id);

            return ResponseEntity.status(201).body(new ComentarioResponseDto(savedComentario));

        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ComentarioResponseDto(ex));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ComentarioResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ComentarioResponseDto(ex));
        }
    }


}
