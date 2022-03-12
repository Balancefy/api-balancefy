package balancefy.api.controller;

import balancefy.api.entities.Usuario;
import balancefy.api.entities.dto.LoginDto;
import balancefy.api.entities.Objetivo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class BalancefyController {
    private List<Usuario> usuarios = new ArrayList();
    private List<Objetivo> objetivos = new ArrayList();

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Usuario usuario) {
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

    @PostMapping("/cadastrar/objetivo")
    public ResponseEntity cadastrarMetas(@RequestBody Objetivo objetivo){
        objetivos.add(objetivo);

        return ResponseEntity.status(201).build();
    }

}
