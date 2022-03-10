package balancefy.api.controller;

import balancefy.api.entities.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class BalancefyController {
    private List<Usuario> usuarios = new ArrayList();
    private String jwtSecret = "D7B0BBEA3A935222C4198C38E30B2EB3E111D11DEA87FA53547EAC1C8A4FF03B";

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Usuario usuario) {
        usuarios.add(usuario);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/autenticar/{email}/{senha}")
    public ResponseEntity login(@PathVariable String email, @PathVariable String senha) {
        String jwtToken = "";
        List<Usuario> usuarioAutenticado = usuarios
                .stream()
                .filter(usuario -> usuario.autenticar(email, senha))
                .collect(Collectors.toList());

        if(!usuarioAutenticado.isEmpty()) {
            Usuario user = usuarioAutenticado.get(0);
            jwtToken = generarToken(user);

            user.setToken(jwtToken);
        }

        return ResponseEntity.status(!usuarioAutenticado.isEmpty() ? 401 : 200).body(jwtToken);
    }

    @GetMapping
    public ResponseEntity listarUsuarios() {
        return ResponseEntity.status(usuarios.isEmpty() ? 204 : 200).body(usuarios);
    }

    private String generarToken(Usuario user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getNome())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 100))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

}
