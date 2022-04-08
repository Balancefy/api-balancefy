package balancefy.api.controller;

import balancefy.api.dto.request.LoginDto;
import balancefy.api.dto.response.UsuarioResponseDto;
import balancefy.api.entities.Usuario;
import balancefy.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar() {
        return ResponseEntity.status(200).body(usuarioService.getUsuarios());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody Usuario usuario) {
        try {
            UsuarioResponseDto account = new UsuarioResponseDto(usuarioService.create(usuario));
            return ResponseEntity.status(200).body(account);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new UsuarioResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
        }
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDto> update(@RequestBody Usuario usuario) {
        try {
            // precisa verificar o token
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
        } catch (HttpClientErrorException.NotFound ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping("/authenticate")
    public ResponseEntity<UsuarioResponseDto> authenticate(@RequestBody LoginDto loginDto) {
        try {
            UsuarioResponseDto account = new UsuarioResponseDto(usuarioService.getUsuarioByLogin(loginDto));
            return ResponseEntity.status(200).body(account);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new UsuarioResponseDto(ex));
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new UsuarioResponseDto(ex));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new UsuarioResponseDto(ex));
        }
    }
}
