package balancefy.api.resources.entities;

import balancefy.api.resources.entities.keys.LikesKey;
import balancefy.api.resources.entities.keys.TaskObjetivoContaKey;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @EmbeddedId
    LikesKey id;

    @ManyToOne
    @MapsId("topicoId")
    @JoinColumn(name = "fk_topico")
    private Topico topico;

    @ManyToOne
    @MapsId("contaId")
    @JoinColumn(name = "fk_conta")
    private Conta conta;

    public Like() {

    }

    public Like(LikesKey likesKey, Topico presentTopic, Conta conta) {
        this.id = likesKey;
        this.topico = presentTopic;
        this.conta = conta;
    }

    public LikesKey getId() {
        return id;
    }

    public void setId(LikesKey id) {
        this.id = id;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
