package balancefy.api.resources.entities.keys;

import javax.persistence.Column;
import java.io.Serializable;

public class TaskObjetivoKey implements Serializable {

    @Column(name = "fk_task")
    private Integer taskId;

    @Column(name = "fk_objetivo")
    private Integer objetivoId;
}
