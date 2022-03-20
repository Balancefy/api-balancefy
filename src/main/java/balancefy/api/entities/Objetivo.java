package balancefy.api.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "objetivo", indexes = {
        @Index(name = "fk_Objetivo_Conta1_idx", columnList = "fkConta")
})
public class Objetivo {
    @Id
    @Column(name = "id_objetivo", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "done")
    private Integer done;

    @Column(name = "valorTotal")
    private Double valorTotal;

    @Column(name = "valorInicial")
    private Double valorInicial;

    @Column(name = "tempo_estimado")
    private Instant tempoEstimado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkConta", nullable = false)
    private Conta fkConta;

    public Conta getFkConta() {
        return fkConta;
    }

    public void setFkConta(Conta fkConta) {
        this.fkConta = fkConta;
    }

    public Instant getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Instant tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}