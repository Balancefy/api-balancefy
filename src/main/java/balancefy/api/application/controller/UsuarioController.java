package balancefy.api.application.controller;

import balancefy.api.application.config.security.TokenService;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.application.dto.response.ListaUsuarioResponseDto;
import balancefy.api.application.dto.response.UsuarioResponseDto;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<ListaUsuarioResponseDto> getList() {
        try {
            return ResponseEntity.status(200).body(new ListaUsuarioResponseDto(usuarioService.getUsuarios()));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(204).body(new ListaUsuarioResponseDto(ex));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ListaUsuarioResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ListaUsuarioResponseDto(ex));
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody @Valid Usuario usuario) {
        try {
            UsuarioResponseDto account = new UsuarioResponseDto(usuarioService.create(usuario));
            return ResponseEntity.status(201).body(account);
        } catch (AlreadyExistsException ex) {
            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new UsuarioResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
        }
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDto> update(@RequestBody Usuario usuario, @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));
            usuario.setId(id);
            UsuarioResponseDto account = new UsuarioResponseDto(usuarioService.update(usuario));
            return ResponseEntity.status(200).body(account);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new UsuarioResponseDto(ex));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new UsuarioResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.status(200).build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PutMapping("/upload/avatar")
    public ResponseEntity updateAvatar(
            @RequestParam("image")MultipartFile multipartFile,
            @RequestHeader(value = "Authorization") String token
    ) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));

            usuarioService.updateAvatar(multipartFile, id);

            return ResponseEntity.ok("Upload Ok");
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (IOException ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }

    }

    @PutMapping("/upload/banner")
    public ResponseEntity updateBanner(
            @RequestParam("image")MultipartFile multipartFile,
            @RequestHeader(value = "Authorization") String token
    ) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));

            usuarioService.updateBanner(multipartFile, id);

            return ResponseEntity.ok("Upload Ok");
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (IOException ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }

    }

}
