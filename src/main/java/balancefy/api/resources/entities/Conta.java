package balancefy.api.resources.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Table(name = "conta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta", nullable = false)
    private Integer id;

    @Column(name = "renda", nullable = false)
    @PositiveOrZero
    private Double renda;

    @Column(name = "progresso", nullable = false)
    private Double progresso = 0.0;

    @Column(name = "status")
    private Integer status = 1;

    @OneToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    @NotNull
    private Usuario fkUsuario;

    public Conta(Integer id, Double renda, Double progresso, Integer status, Usuario fkUsuario) {
        this.id = id;
        this.renda = renda;
        this.progresso = progresso;
        this.status = status;
        this.fkUsuario = fkUsuario;
    }

    public Conta() {

    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", renda=" + renda +
                ", progresso=" + progresso +
                ", status=" + status +
                ", fkUsuario=" + fkUsuario +
                '}';
    }

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

    public void setStatus(Integer status) {this.status = status;}

    public Integer getStatus() {return status;}
}