package balancefy.api.application.dto.response;


import balancefy.api.resources.entities.Movimentacao;

import java.time.LocalDateTime;

public class MovimentacaoResponseDto {
    private Integer id;
    private Double valor;
    private String topico;
    private String descricao;
    private String tipo;
    private LocalDateTime createdAt;

    public MovimentacaoResponseDto() {
    }

    public MovimentacaoResponseDto(Movimentacao m) {
        this.id = m.getId();
        this.valor = m.getValor();
        this.topico = m.getTopico();
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

    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
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
