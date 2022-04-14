package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Usuario;

import java.time.LocalDate;

public class UsuarioResponseDto extends ResponseDto {

    private Integer id;
    private String nome;
    private LocalDate dataNasc;

    public UsuarioResponseDto(Usuario usuario) {
        super("Sucesso");
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.dataNasc = usuario.getDataNasc();
    }

    public UsuarioResponseDto(Exception ex) {
        super(ex.getMessage());
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
