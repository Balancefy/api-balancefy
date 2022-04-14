package balancefy.api.resources.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta", nullable = false)
    private Integer id;

    @Column(name = "renda", nullable = false)
    @NotBlank
    private Double renda;

    @Column(name = "progresso", nullable = false)
    private Double progresso = 0.0;

    @Column(name = "status")
    private Integer status = 1;

    @OneToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    @NotNull
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