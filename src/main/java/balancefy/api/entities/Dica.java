package balancefy.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "dica", indexes = {
        @Index(name = "fk_Dica_Conta1_idx", columnList = "fk_conta")
})
public class Dica {
    @Id
    @Column(name = "id_dicas", nullable = false)
    private Integer id;

    @Column(name = "titulo", length = 45)
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tema", length = 45)
    private String tema;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_conta", nullable = false)
    private Conta fkConta;

    public Conta getFkConta() {
        return fkConta;
    }

    public void setFkConta(Conta fkConta) {
        this.fkConta = fkConta;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}