package balancefy.api.dto.response;

import balancefy.api.entities.Usuario;

import java.time.LocalDate;

public class UsuarioResponseDto {

    private Integer id;
    private String nome;
    private LocalDate dataNasc;

    public UsuarioResponseDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.dataNasc = usuario.getDataNasc();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }
}
