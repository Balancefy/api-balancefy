package balancefy.api.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacaoFixa")
public class MovimentacaoFixa {
    @Id
    @Column(name = "id_movimentacao_fixa", nullable = false)
    private Integer id;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "topico")
    private Double topico;

    @Column(name = "descricao")
    private Double descricao;

    @Column(name = "tipo", length = 100)
    private String tipo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_conta", nullable = false)
    private Conta fkConta;

    public Conta getFkObjetivo() {
        return fkConta;
    }

    public void setFkObjetivo(Conta fkConta) {
        this.fkConta = fkConta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getTopico() {
        return topico;
    }

    public void setTopico(Double topico) {
        this.topico = topico;
    }

    public Double getDescricao() {
        return descricao;
    }

    public void setDescricao(Double descricao) {
        this.descricao = descricao;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValorTotal() {
        return valor;
    }

    public void setValorTotal(Double valorTotal) {
        this.valor = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}