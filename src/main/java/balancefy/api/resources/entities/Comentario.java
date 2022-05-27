package balancefy.api.resources.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @Column(name = "id_comentario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "fk_conta")
    private Conta fkConta;

    @ManyToOne
    @JoinColumn(name = "fk_topico")
    private Topico fkTopico;

    @ManyToOne
    @JoinColumn(name = "fk_comentario")
    private Comentario fkComentario;

    @OneToMany(mappedBy = "fkComentario")
    private List<Comentario> comentarios = new ArrayList<>();

    public Comentario() {
    }

    public Comentario(String conteudo, Conta conta, Topico topico, Comentario comentario) {
        this.conteudo = conteudo;
        this.createdAt = LocalDateTime.now();
        this.fkConta = conta;
        this.fkTopico = topico;
        this.fkComentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Conta getFkConta() {
        return fkConta;
    }

    public void setFkConta(Conta fkConta) {
        this.fkConta = fkConta;
    }

    public Topico getFkTopico() {
        return fkTopico;
    }

    public void setFkTopico(Topico fkTopico) {
        this.fkTopico = fkTopico;
    }

    public Comentario getFkComentario() {
        return fkComentario;
    }

    public void setFkComentario(Comentario fkComentario) {
        this.fkComentario = fkComentario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}