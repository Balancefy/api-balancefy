package balancefy.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "conta", indexes = {
        @Index(name = "fk_Conta_Usuario1_idx", columnList = "fk_usuario")
})
public class Conta {
    @Id
    @Column(name = "id_conta", nullable = false)
    private Integer id;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    @Column(name = "renda", nullable = false)
    private Double renda;

    @Column(name = "qtd_objetivos_completos")
    private Integer qtdObjetivosCompletos;

    @Column(name = "progresso", nullable = false)
    private Double progresso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario fkUsuario;

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Double getProgresso() {
        return progresso;
    }

    public void setProgresso(Double progresso) {
        this.progresso = progresso;
    }

    public Integer getQtdObjetivosCompletos() {
        return qtdObjetivosCompletos;
    }

    public void setQtdObjetivosCompletos(Integer qtdObjetivosCompletos) {
        this.qtdObjetivosCompletos = qtdObjetivosCompletos;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}