package balancefy.api.resources.entities.keys;

import javax.persistence.Column;
import java.io.Serializable;

public class TaskObjetivoContaKey implements Serializable {

    @Column(name = "fk_task_objetivo")
    private Integer taskId;

    @Column(name = "fk_objetivo_conta")
    private Integer objetivoContaId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getObjetivoContaId() {
        return objetivoContaId;
    }

    public void setObjetivoContaId(Integer objetivoContaId) {
        this.objetivoContaId = objetivoContaId;
    }
}
