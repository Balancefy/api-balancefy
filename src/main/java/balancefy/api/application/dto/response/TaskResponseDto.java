package balancefy.api.application.dto.response;


import balancefy.api.resources.entities.keys.TaskObjetivoContaKey;

import java.time.LocalDateTime;

public class TaskResponseDto {
    private TaskObjetivoContaKey id;
    private Integer ordem;
    private String descricao;
    private Integer done;
    private Double pontuacao;
    private Double valor;
    private LocalDateTime createdAt;

    public TaskResponseDto(TaskObjetivoContaKey id, Integer ordem, String descricao, Integer done, Double pontuacao, Double valor, LocalDateTime createdAt) {
        this.id = id;
        this.ordem = ordem;
        this.descricao = descricao;
        this.done = done;
        this.pontuacao = pontuacao;
        this.valor = valor;
        this.createdAt = createdAt;
    }

    public TaskObjetivoContaKey getId() {
        return id;
    }

    public void setId(TaskObjetivoContaKey id) {
        this.id = id;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
