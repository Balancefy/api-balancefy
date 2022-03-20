package balancefy.api.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "movimentacao", indexes = {
        @Index(name = "fk_Movimentacao_Conta1_idx", columnList = "fk_conta"),
        @Index(name = "fk_Movimentacao_Objetivo1_idx", columnList = "fk_objetivo")
})
public class Movimentacao {
    @Id
    @Column(name = "id_movimentacao", nullable = false)
    private Integer id;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "tipo", length = 100)
    private String tipo;

    @Column(name = "create_at")
    private Instant createAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_objetivo", nullable = false)
    private Objetivo fkObjetivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_conta", nullable = false)
    private Conta fkConta;

    public Conta getFkConta() {
        return fkConta;
    }

    public void setFkConta(Conta fkConta) {
        this.fkConta = fkConta;
    }

    public Objetivo getFkObjetivo() {
        return fkObjetivo;
    }

    public void setFkObjetivo(Objetivo fkObjetivo) {
        this.fkObjetivo = fkObjetivo;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}