package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Usuario;

import java.time.LocalDate;

public class LoginResponseDto extends ResponseDto {
    private String token;
    private Conta conta;

    public LoginResponseDto(Conta conta, String token) {
        super("Sucesso");
        this.token = token;
        this.conta = conta;
    }


    public String getToken() {
        return token;
    }

    public Conta getConta() {
        return conta;
    }
}
