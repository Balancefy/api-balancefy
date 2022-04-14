package balancefy.api.resources.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @Column(name = "id_comentario", nullable = false)
    private Integer id;

    @Column(name = "curtida")
    private Integer qtdCurtidas;

    @Column(name = "visualizacao")
    private Integer qtdVisualizacoes;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_conta")
    private Conta fkConta;

    @ManyToOne
    @JoinColumn(name = "fk_topico")
    private Topico fkTopico;

    @ManyToOne
    @JoinColumn(name = "fk_comentario")
    private Comentario fkComentario;

    public Comentario getFkComentario() {
        return fkComentario;
    }

    public void setFkComentario(Comentario comentario) {
        this.fkComentario = comentario;
    }

    public Topico getFkTopico() {
        return fkTopico;
    }

    public void setFkTopico(Topico topico) {
        this.fkTopico = topico;
    }

    public Conta getFkConta() {
        return fkConta;
    }

    public void setFkConta(Conta fkConta) {
        this.fkConta = fkConta;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getData() {
        return createdAt;
    }

    public Integer getQtdVisualizacoes() {
        return qtdVisualizacoes;
    }

    public void setQtdVisualizacoes(Integer qtdVisualizacoes) {
        this.qtdVisualizacoes = qtdVisualizacoes;
    }

    public Integer getQtdCurtidas() {
        return qtdCurtidas;
    }

    public void setQtdCurtidas(Integer qtdCurtidas) {
        this.qtdCurtidas = qtdCurtidas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}