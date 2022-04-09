package balancefy.api.dto.response;

import balancefy.api.entities.Movimentacao;
import balancefy.api.entities.Objetivo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class MovimentacaoResponseDto {
    private Integer id;
    private Double valor;
    private Double topico;
    private Double descricao;
    private String tipo;
    private LocalDateTime createdAt;

    public MovimentacaoResponseDto(Movimentacao m){
        this.id = m.getId();
        this.valor = m.getValor();
        this.topico = m.getValor();
        this.descricao = m.getDescricao();
        this.tipo = m.getTipo();
        this.createdAt = m.getCreatedAt();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getTopico() {
        return topico;
    }

    public void setTopico(Double topico) {
        this.topico = topico;
    }

    public Double getDescricao() {
        return descricao;
    }

    public void setDescricao(Double descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
