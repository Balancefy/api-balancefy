package balancefy.api.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {
    @Id
    @Column(name = "id_movimentacao", nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "fk_objetivo", nullable = false)
    private Objetivo fkObjetivo;

    public Objetivo getFkObjetivo() {
        return fkObjetivo;
    }

    public void setFkObjetivo(Objetivo fkObjetivo) {
        this.fkObjetivo = fkObjetivo;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
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