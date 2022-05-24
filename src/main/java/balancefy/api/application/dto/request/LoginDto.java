package balancefy.api.application.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
