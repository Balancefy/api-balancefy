package balancefy.api.controller;

import balancefy.api.entities.Conta;
import balancefy.api.entities.MovimentacaoFixa;
import balancefy.api.entities.Usuario;
import balancefy.api.entities.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class BalancefyController {
    private List<Usuario> usuarios = new ArrayList();
    private List<Conta> contas = new ArrayList();

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(usuario.getEmail())) {
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
    public ResponseEntity logOffUsuario(@PathVariable String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
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

    @GetMapping("/conta")
    public List<Conta> listaConta() {
        return contas;
    }

    @PostMapping("/conta/cadastrar")
    public ResponseEntity cadastrarConta(@RequestBody Conta conta) {
        for (Conta c : contas) {
            if (c.getIdUsuario() == conta.getIdUsuario()) {
                return ResponseEntity.status(409).body("Conta ja cadastrado");
            }
        }
        // PASSOU!!! A NAO CONTA EXISTE ->   ( CAMINHO FELIZ DE CADASTRAR CONTA )

        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == conta.getIdUsuario()) {
                contas.add(conta);
                return ResponseEntity.status(201).body(conta);
                // CONTA CADASTRADA !!!!
            }
        }
        return ResponseEntity.status(400).body("http.cat/400");
    }

    @DeleteMapping("/conta/{indice}")
    public ResponseEntity deletarConta(@PathVariable int indice) {
        if (indice >= contas.size() || indice < 0) {
            return ResponseEntity.status(400).body("Indice nao encontrado");
        }

        contas.remove(indice);
        return ResponseEntity.status(200).body("Conta Removida");
    }

    @PostMapping("/conta/movimentacaoFixa/{indiceConta}")
    public ResponseEntity cadastrarMovimentacaoFixa(
            @RequestBody MovimentacaoFixa mf,
            @PathVariable int indiceConta) {
        if (indiceConta < 0 || indiceConta >= contas.size()) {
            return ResponseEntity.status(400).body("indice da conta nao encontrado");
        }
        contas.get(indiceConta).cadastrarMovimentacaoFixa(mf);
        return ResponseEntity.status(200).body("movimentacao cadastrada");
    }

    @PutMapping("/conta/movimentacaoFixa/{indiceConta}/{indiceMov}")
    public ResponseEntity alterarMovimentacaoFixa(
            @RequestBody MovimentacaoFixa mf,
            @PathVariable int indiceConta,
            @PathVariable int indiceMov) {

        if (indiceConta < 0 || indiceConta >= contas.size()) {
            return ResponseEntity.status(400).body("indice nao encontrado");
        }

        contas.get(indiceConta).atualizarMovimentacaoFixa(indiceMov, mf);
        return ResponseEntity.status(200).body("movimentacao alterada");
    }

    @DeleteMapping("/conta/movimentacaoFixa/{indiceConta}/{indiceMov}")
    public ResponseEntity deletarMovimentacaoFixa(
            @PathVariable int indiceConta,
            @PathVariable int indiceMov) {
        if (indiceConta < 0 || indiceConta >= contas.size()) {
            return ResponseEntity.status(400).body("indiceConta nao encontrado");
        }
        if (indiceMov < 0 || indiceMov >= contas.size()) {
            return ResponseEntity.status(404).body("indiceMovimentacao nao existe");
        }
        contas.get(indiceMov).deletarMovimentacaoFixa(indiceMov);
        return ResponseEntity.status(200).body("movimentacao deletada");
    }

    @GetMapping("/conta/movimentacaoFixa/{indiceConta}")
    public List<MovimentacaoFixa> listaMovimentacaoConta(@PathVariable int indiceConta) {
        return contas.get(indiceConta).listaMovimentacaoFixa();
    }
}