package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.TypeUser;
import balancefy.api.resources.entities.Usuario;

import javax.persistence.Column;
import java.time.LocalDate;

public class UsuarioResponseDto extends ResponseDto {

    private Integer id;
    private String nome;
    private LocalDate dataNasc;
    private String avatar;
    private String banner;
    private TypeUser tipo;

    public UsuarioResponseDto(Usuario usuario) {
        super("Sucesso");
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.dataNasc = usuario.getDataNasc();
        this.avatar = usuario.getAvatar();
        this.banner = usuario.getBanner();
        this.tipo = usuario.getTipo();
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

    public String getAvatar() {
        return avatar;
    }

    public String getBanner() {
        return banner;
    }

    public TypeUser getTipo() {
        return tipo;
    }
}
