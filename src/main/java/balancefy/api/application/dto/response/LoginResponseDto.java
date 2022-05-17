package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

public class LoginResponseDto extends ResponseDto {
    private String token;
    private ContaResponseDto conta;

    public LoginResponseDto(Conta conta, String token) {
        super("Sucesso");
        this.token = token;
        this.conta = new ContaResponseDto(conta);
    }

    public LoginResponseDto(Exception ex) {
        super(ex.getMessage());
    }


    public String getToken() {
        return token;
    }

    public ContaResponseDto getConta() {
        return conta;
    }
}
