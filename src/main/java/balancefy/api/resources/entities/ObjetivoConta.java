package balancefy.api.resources.entities;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "objetivoconta")
public class ObjetivoConta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objetivo_conta", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_conta")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name ="fk_objetivo")
    private Objetivo objetivo;

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

    @Column(name= "pontuacao")
    private Double pontuacao;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ObjetivoConta() { }

    public ObjetivoConta(Conta conta, Objetivo objetivo, String descricao, Integer done, Double valorTotal, Double valorInicial, Instant tempoEstimado, Double pontuacao) {
        this.conta = conta;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.done = done;
        this.valorTotal = valorTotal;
        this.valorInicial = valorInicial;
        this.tempoEstimado = tempoEstimado;
        this.pontuacao = pontuacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Instant getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Instant tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }
}
