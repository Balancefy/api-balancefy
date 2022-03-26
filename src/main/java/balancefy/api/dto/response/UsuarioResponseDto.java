package balancefy.api.dto.response;

import java.time.LocalDate;

public class UsuarioResponseDto {

    private Integer id;
    private String nome;
    private LocalDate dataNasc;

    public UsuarioResponseDto(Integer id, String nome, LocalDate dataNasc) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
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
