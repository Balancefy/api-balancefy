package balancefy.api.application.dto.request;

import balancefy.api.resources.entities.Objetivo;

import java.time.Instant;

public class ObjetivoDto {

    private Objetivo objetivo;

    private String descricao;

    private Double valorTotal;

    private Double valorInicial;

    private Instant tempoEstimado;

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Instant getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Instant tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }
}
