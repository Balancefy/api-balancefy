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

    @Column(name = "renda", nullable = false)
    private Double renda;

    @Column(name = "progresso", nullable = false)
    private Double progresso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario fkUsuario;

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario usuario) {
        this.fkUsuario = usuario;
    }

    public Double getProgresso() {
        return progresso;
    }

    public void setProgresso(Double progresso) {
        this.progresso = progresso;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}