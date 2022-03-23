package balancefy.api.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacaoFixa", indexes = {
        @Index(name = "fk_Movimentacao_Conta1_idx", columnList = "fk_conta")
})
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

    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_conta", nullable = false)
    private Conta fkConta;

    public Conta getFkObjetivo() {
        return fkConta;
    }

    public void setFkObjetivo(Conta fkConta) {
        this.fkConta = fkConta;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
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