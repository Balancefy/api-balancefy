package balancefy.api.resources.entities.keys;

import javax.persistence.Column;
import java.io.Serializable;

public class LikesKey implements Serializable {
    @Column(name = "fk_topico")
    private Integer topicoId;

    @Column(name = "fk_conta")
    private Integer contaId;

    public LikesKey(Integer topicoId, Integer contaId) {
        this.topicoId = topicoId;
        this.contaId = contaId;
    }

    public LikesKey() {}


}
