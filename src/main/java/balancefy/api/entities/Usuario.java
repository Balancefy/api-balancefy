package balancefy.api.entities;

import balancefy.api.entities.dto.LoginDto;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.crypto.spec.SecretKeySpec;
import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idUsuario;

    @Column(length = 100)
    private String nome;

    @Column(length = 100)
    private String email;

    @Column(length = 155)
    private String senha;

    @Column
    private LocalDate dataNascimento;

    private String token = "";

    public boolean autenticar(LoginDto login) {
        boolean validacao = login.getEmail().equals(this.email) && login.getSenha().equals(this.senha);
        token = validacao ? gerarToken() : "";

        return validacao;
    }

    private String gerarToken() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secret123");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(this.nome)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
