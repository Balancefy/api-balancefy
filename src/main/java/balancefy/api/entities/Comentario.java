package balancefy.api.entities;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentario", indexes = {
        @Index(name = "fk_Comentario_Comentario1_idx", columnList = "fk_comentario"),
        @Index(name = "fk_Comentario_Conta1_idx", columnList = "fk_conta"),
        @Index(name = "fk_Comentario_Topico1_idx", columnList = "fk_topico")
})
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
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_conta", nullable = false)
    private Conta fkConta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_topico", nullable = false)
    private Topico fkTopico;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_comentario", nullable = false)
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
        return createAt;
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