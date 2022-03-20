package balancefy.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "rank", indexes = {
        @Index(name = "fk_Rank_Usuario_idx", columnList = "fk_usuario")
})
public class Rank {
    @Id
    @Column(name = "id_rank", nullable = false)
    private Integer id;

    @Column(name = "posicao")
    private Integer posicao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_usuario", nullable = false)
    private Usuario fkUsuario;

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}