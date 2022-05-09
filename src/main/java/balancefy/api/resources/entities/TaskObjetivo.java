package balancefy.api.resources.entities;

import javax.persistence.*;

@Entity
public class TaskObjetivo {

    @Id
    Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_task")
    Task task;

    @ManyToOne
    @JoinColumn(name = "fk_objetivo")
    Objetivo objetivo;

    private Integer ordem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
