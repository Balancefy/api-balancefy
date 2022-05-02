package balancefy.api.application.config.security;

import balancefy.api.resources.entities.DetalheUser;
import balancefy.api.resources.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        DetalheUser logado = (DetalheUser) authentication.getPrincipal();
        Date created = new Date();
        LocalDate dataExpiracao = created.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(1);

        return Jwts.builder()
                .setIssuer("Balancefy")
                .setSubject(logado.getId())
                .setIssuedAt(created)
                .setExpiration(Date.from(dataExpiracao.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String gerarTokenRedeSocial(DetalheUser logado) {
        Date created = new Date();
        LocalDate dataExpiracao = created.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusMonths(1);

        return Jwts.builder()
                .setIssuer("Balancefy")
                .setSubject(logado.getId())
                .setIssuedAt(created)
                .setExpiration(Date.from(dataExpiracao.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
}
