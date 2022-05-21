package balancefy.api.resources.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
public class Topico {
    @Id
    @Column(name = "id_topico", nullable = false)
    private Integer id;

    @Column(name = "titulo", length = 45)
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "liked")
    private Integer liked;

    @Column(name = "viewed")
    private Integer viewed;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_conta")
    private Conta fkConta;

    public Topico(String titulo, String conteudo, Conta conta) {
        this.liked = 0;
        this.viewed = 0;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.createdAt = LocalDateTime.now();
        this.fkConta = conta;
    }

    public Integer adicionarLike() {
        return this.liked++;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String descricao) {
        this.conteudo = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Conta getFkConta() {
        return fkConta;
    }

    public void setFkConta(Conta fkConta) {
        this.fkConta = fkConta;
    }
}