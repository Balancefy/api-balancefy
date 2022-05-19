package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Objetivo;
import balancefy.api.resources.entities.ObjetivoConta;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.time.LocalDateTime;

public class ObjetivoContaResponseDto {

    private Integer id;
    private String categoria;
    private String descricao;
    private Integer done;
    private Double valorTotal;
    private Double valorInicial;
    private Instant tempoEstimado;
    private Double pontuacao;
    private LocalDateTime createdAt;


    public ObjetivoContaResponseDto(ObjetivoConta objetivo) {
        this.id = objetivo.getId();
        this.categoria = objetivo.getObjetivo().getCategoria();
        this.descricao = objetivo.getDescricao();
        this.done = objetivo.getDone();
        this.valorTotal = objetivo.getValorTotal();
        this.valorInicial = objetivo.getValorInicial();
        this.tempoEstimado = objetivo.getTempoEstimado();
        this.pontuacao = objetivo.getPontuacao();
        this.createdAt = objetivo.getCreatedAt();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
}
