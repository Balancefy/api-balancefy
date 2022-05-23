package balancefy.api.application.controller;

import balancefy.api.application.config.security.TokenService;
import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.application.dto.response.ListaFeedTopicoResponse;
import balancefy.api.application.dto.response.TopicoResponseDto;
import balancefy.api.domain.services.ComentarioService;
import balancefy.api.domain.services.TopicoService;
import balancefy.api.resources.entities.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<ListaFeedTopicoResponse> get(@RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));

            return ResponseEntity.status(201).body(new ListaFeedTopicoResponse(topicoService.getTopicoFeed(id)));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ListaFeedTopicoResponse(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ListaFeedTopicoResponse(ex));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDto> get(@PathVariable int id) {
        try {
            Topico foundTopico = topicoService.getTopicoById(id);
            TopicoResponseDto account = new TopicoResponseDto(foundTopico, topicoService.getTopicoLikes(foundTopico));
            return ResponseEntity.status(201).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new TopicoResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new TopicoResponseDto(ex));
        }
    }

    @PostMapping
    public ResponseEntity<TopicoResponseDto> create(@RequestBody TopicoRequestDto topico,
                                                    @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));
            Topico savedTopico = topicoService.create(topico, id);

            TopicoResponseDto account = new TopicoResponseDto(savedTopico, topicoService.getTopicoLikes(savedTopico));
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
            Topico updatedTopico = topicoService.update(topico, id);
            TopicoResponseDto account = new TopicoResponseDto(updatedTopico, topicoService.getTopicoLikes(updatedTopico));
            return ResponseEntity.status(200).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new TopicoResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new TopicoResponseDto(ex));
        }
    }

    @PatchMapping("/like/{topicId}")
    public ResponseEntity<TopicoResponseDto> addLike(@PathVariable int topicId, @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));

            topicoService.addLike(topicId, id);

            Topico topico = topicoService.getTopicoById(topicId);

            TopicoResponseDto account = new TopicoResponseDto(topico, topicoService.getTopicoLikes(topico));
            return ResponseEntity.status(200).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new TopicoResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new TopicoResponseDto(ex));
        }
    }

    @PatchMapping("/unlike/{topicId}")
    public ResponseEntity<TopicoResponseDto> removeLike(@PathVariable int topicId, @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));

            topicoService.removeLike(topicId, id);
            Topico topico = topicoService.getTopicoById(topicId);

            TopicoResponseDto account = new TopicoResponseDto(topico, topicoService.getTopicoLikes(topico));
            return ResponseEntity.status(200).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new TopicoResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new TopicoResponseDto(ex));
        }
    }
}
