package balancefy.api.resources.entities;

import balancefy.api.application.dto.request.UsuarioRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotBlank
    private String nome;

    @Column(name = "email", length = 100)
    @NotBlank
    private String email;

    @Column(name = "senha", length = 100)
    private String senha;

    @Column(name = "avatar", length = 100)
    private String avatar;

    @Column(name = "banner", length = 100)
    private String banner;

    @Column(name = "data_nasc")
    @PastOrPresent
    private LocalDate dataNasc;

    @Column(name = "tipo")
    private TypeUser type = TypeUser.DEFAULT;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Usuario() {
    }

    public Usuario(UsuarioRequest usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.avatar = usuario.getAvatar();
        this.banner = usuario.getBanner();
        this.dataNasc = usuario.getDataNasc();
        this.createdAt = LocalDateTime.now();
    }

    public Usuario(Integer id, String nome, String email, String senha, String avatar, String banner, LocalDate dataNasc, TypeUser type, LocalDateTime createdAt) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.avatar = avatar;
        this.banner = banner;
        this.dataNasc = dataNasc;
        this.type = type;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", avatar='" + avatar + '\'' +
                ", banner='" + banner + '\'' +
                ", dataNasc=" + dataNasc +
                ", type=" + type +
                ", createdAt=" + createdAt +
                '}';
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {

        if (avatar.endsWith(".png") || avatar.endsWith(".jpeg") || banner.endsWith(".jpg")) {
            return "/user-photos/" + id + "/avatar/" + avatar;
        }

        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        if (banner.endsWith(".png") || banner.endsWith(".jpeg") || banner.endsWith(".jpg")) {
            return "/user-photos/" + id + "/banner/" + banner;
        }

        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}