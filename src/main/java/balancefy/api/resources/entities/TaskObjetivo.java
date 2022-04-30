package balancefy.api.resources.entities;

import balancefy.api.resources.entities.keys.TaskObjetivoKey;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

public class TaskObjetivo {

    @EmbeddedId
    TaskObjetivoKey id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "fk_task")
    Task task;

    @ManyToOne
    @MapsId("objetivoId")
    @JoinColumn(name = "fk_objetivo")
    Objetivo objetivo;

    private Integer ordem;

    public TaskObjetivoKey getId() {
        return id;
    }

    public void setId(TaskObjetivoKey id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
