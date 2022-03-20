package balancefy.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "task", indexes = {
        @Index(name = "fk_Task_Objetivo1_idx", columnList = "fk_objetivo")
})
public class Task {
    @Id
    @Column(name = "id_task", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "done")
    private Integer done;

    @Column(name = "qtd_desconto")
    private Double qtdDesconto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_objetivo", nullable = false)
    private Objetivo fkObjetivo;

    public Objetivo getFkObjetivo() {
        return fkObjetivo;
    }

    public void setFkObjetivo(Objetivo fkObjetivo) {
        this.fkObjetivo = fkObjetivo;
    }

    public Double getQtdDesconto() {
        return qtdDesconto;
    }

    public void setQtdDesconto(Double qtdDesconto) {
        this.qtdDesconto = qtdDesconto;
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