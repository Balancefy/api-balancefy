package balancefy.api.application.dto.request;

import balancefy.api.resources.entities.Objetivo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class ObjetivoDto {

    private Objetivo objetivo;

    private String descricao;

    private Double valorTotal;

    private Double valorInicial;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Future
    private LocalDate tempoEstimado;

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

    public LocalDate getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(LocalDate tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }
}
