package balancefy.api.application.dto.request;

import balancefy.api.resources.entities.Conta;

import java.time.LocalDateTime;

public class MovimentacaoFixaRequestDto {
    private Double valor;
    private String categoria;
    private String descricao;
    private String tipo;

    public MovimentacaoFixaRequestDto(Double valor, String categoria, String descricao, String tipo) {
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;
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
}
