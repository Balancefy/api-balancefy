package balancefy.api.controller;

import balancefy.api.entities.Usuario;
import balancefy.api.entities.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class BalancefyController {
    private List<Usuario> usuarios = new ArrayList();

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Usuario usuario) {
        for (Usuario u : usuarios){
            if (u.getEmail().equals(usuario.getEmail())){
                return ResponseEntity.status(409).body("Usuario ja cadastrado");
            }
        }
        usuarios.add(usuario);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/autenticar")
    public ResponseEntity login(@RequestBody LoginDto login) {
        Optional<Usuario> usuarioAutenticado = usuarios
                .stream()
                .filter(usuario -> usuario.autenticar(login))
                .findFirst();

        return ResponseEntity.status(usuarioAutenticado.isPresent() ? 200 : 401).body(usuarioAutenticado.get().getToken());
    }

    @GetMapping
    public ResponseEntity listarUsuarios() {
        return ResponseEntity.status(usuarios.isEmpty() ? 204 : 200).body(usuarios);
    }

    @DeleteMapping("/logOffUsuario/{email}")
    public ResponseEntity logOffUsuario(@PathVariable String email){
        for (Usuario u : usuarios){
            if (u.getEmail().equals(email)){
                u.setToken("");
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/logOffAll")
    public ResponseEntity logOffAll() {
        for (Usuario user : usuarios) {
            user.setToken("");
        }
        return ResponseEntity.status(200).build();
    }
}
