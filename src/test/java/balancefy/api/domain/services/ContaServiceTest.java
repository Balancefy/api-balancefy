package balancefy.api.domain.services;

import balancefy.api.application.dto.request.UsuarioRequest;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContaServiceTest {

    @Test
    void create() {
        ContaService contaService = new ContaService();

//        contaService.create()
    }

    private Conta getConta(){
        Conta c1 = new Conta(1,1000.00, 0.0, 1 , 1);
        return c1;
    }

    private Usuario getUsuario(){
        Usuario u1 = new Usuario(
                1,
                "Verdinha",
                "aaaa@gmail.com",
                "verdiner123",
                "foto.jpeg",
                "fundo.jpeg",
                "2000/05/24",
                1,
                "2022/03/03"
        );
        return u1;
    }

    public Usuario(UsuarioRequest usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.avatar = usuario.getAvatar();
        this.banner = usuario.getBanner();
        this.dataNasc = usuario.getDataNasc();
        this.status = 1;
        this.createdAt = LocalDateTime.now();
    }

    @Test
    void update() {
    }

    @Test
    void getContaById() {
    }

    @Test
    void delete() {
    }

    @Test
    void updateProgress() {
    }
}