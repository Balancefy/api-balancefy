package balancefy.api.controller;

import balancefy.api.dto.request.LoginDto;
import balancefy.api.entities.Usuario;
import balancefy.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.status(200).body(usuarioService.getUsuarios());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(201).body(usuarioService.create(usuario));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Usuario usuario) {
        try {
            // precisa verificar o token
            return ResponseEntity.status(200).body(usuarioService.update(usuario));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.status(200).build();
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity authenticate(@RequestBody LoginDto loginDto) {
        try {
            return ResponseEntity.status(200).body(usuarioService.getUsuarioByLogin(loginDto));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
}
