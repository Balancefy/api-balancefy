package balancefy.api.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UsuarioSenhaRequestDto {

    @NotBlank
    private String senhaAtual;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$", message = "Senha inválida")
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
