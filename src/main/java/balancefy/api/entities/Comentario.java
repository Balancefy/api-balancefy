package balancefy.api.entities;

import java.time.LocalDateTime;

public class Comentario {
    private Integer idComentario;
    private Integer qtdCurtidas;
    private Integer qtdVisualizacoes;
    private LocalDateTime data;
    private String conteudo;

    public Comentario(Integer idComentario, Integer qtdCurtidas, Integer qtdVisualizacoes, LocalDateTime data, String conteudo) {
        this.idComentario = idComentario;
        this.qtdCurtidas = qtdCurtidas;
        this.qtdVisualizacoes = qtdVisualizacoes;
        this.data = data;
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "idComentario=" + idComentario +
                ", qtdCurtidas=" + qtdCurtidas +
                ", qtdVisualizacoes=" + qtdVisualizacoes +
                ", data=" + data +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public Integer getQtdCurtidas() {
        return qtdCurtidas;
    }

    public void setQtdCurtidas(Integer qtdCurtidas) {
        this.qtdCurtidas = qtdCurtidas;
    }

    public Integer getQtdVisualizacoes() {
        return qtdVisualizacoes;
    }

    public void setQtdVisualizacoes(Integer qtdVisualizacoes) {
        this.qtdVisualizacoes = qtdVisualizacoes;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
