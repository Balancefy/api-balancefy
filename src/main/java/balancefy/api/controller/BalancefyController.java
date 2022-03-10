package balancefy.api.controller;

import balancefy.api.entities.Usuario;
import io.jsonwebtoken.Jwts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class BalancefyController {
    private List<Usuario> usuarios = new ArrayList();

    @PostMapping
    private ResponseEntity cadastrar(@RequestBody Usuario usuario) {
        usuarios.add(usuario);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/autenticar/{email}/{senha}")
    private ResponseEntity login(@PathVariable String email, @PathVariable String senha) {
        String jwtToken = "";
        long autenticado = usuarios.stream().filter( usuario -> usuario.autenticar(email, senha)).count();

        if(autenticado != 0) {
            jwtToken = Jwts.builder().
                    .compact();
        }

        return ResponseEntity.status(autenticado == 0 ? 401 : 200).body(jwtToken);
    }

}
