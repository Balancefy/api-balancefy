package balancefy.api.application.dto.request;

import javax.validation.constraints.NotBlank;

public class UsuarioSenhaRequestDto {

    @NotBlank
    private String senhaAtual;
    private String novaSenha;

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
