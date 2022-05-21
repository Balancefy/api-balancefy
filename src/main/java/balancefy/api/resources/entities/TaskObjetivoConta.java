package balancefy.api.resources.entities;

import balancefy.api.resources.entities.keys.TaskObjetivoContaKey;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "taskobjetivoconta")
public class TaskObjetivoConta {

    @EmbeddedId
    TaskObjetivoContaKey id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "fk_task_objetivo")
    TaskObjetivo task;

    @ManyToOne
    @MapsId("objetivoContaId")
    @JoinColumn(name = "fk_objetivo_conta")
    ObjetivoConta objetivoConta;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "done")
    private Integer done;

    @Column(name = "pontuacao")
    private Double pontuacao;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public TaskObjetivoConta() {

    }

    public TaskObjetivoContaKey getId() {
        return id;
    }

    public void setId(TaskObjetivoContaKey id) {
        this.id = id;
    }

    public TaskObjetivo getTask() {
        return task;
    }

    public void setTask(TaskObjetivo task) {
        this.task = task;
    }

    public ObjetivoConta getObjetivoConta() {
        return objetivoConta;
    }

    public void setObjetivoConta(ObjetivoConta objetivoConta) {
        this.objetivoConta = objetivoConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public TaskObjetivoConta(TaskObjetivoContaKey id, TaskObjetivo task, ObjetivoConta objetivoConta, String descricao, Integer done, Double pontuacao) {
        this.id = id;
        this.task = task;
        this.objetivoConta = objetivoConta;
        this.descricao = descricao;
        this.done = done;
        this.pontuacao = pontuacao;
    }
}
