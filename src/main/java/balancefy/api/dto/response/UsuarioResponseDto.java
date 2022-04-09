package balancefy.api.dto.response;

import balancefy.api.entities.Usuario;

import java.time.LocalDate;

public class UsuarioResponseDto extends ResponseDto {

    private Integer id;
    private String nome;
    private LocalDate dataNasc;
    private String avatar;
    private String banner;

    public UsuarioResponseDto(Usuario usuario) {
        super("Sucesso");
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.dataNasc = usuario.getDataNasc();
        this.avatar = usuario.getAvatar();
        this.banner = usuario.getBanner();
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
}
