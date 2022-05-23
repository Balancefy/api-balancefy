package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Conta;

import java.time.LocalDateTime;

public class MovimentacaoFixaDto  {
    private Integer id;
    private Double valor;
    private String categoria;
    private String descricao;
    private String tipo;
    private LocalDateTime createdAt;


    public MovimentacaoFixaDto(Integer id, Double valor, String categoria, String descricao, String tipo, LocalDateTime createdAt) {
        this.id = id;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;
        this.createdAt = createdAt;
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
