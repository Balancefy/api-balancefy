package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;

import java.time.LocalDateTime;
import java.util.List;


public class ComentarioDto {
    private int id;
    private String conteudo;
    private LocalDateTime createdAt;
    private Integer conta;
    private Integer fkTopico;
    private Integer fkComentario;
    private List<Comentario> comentarios;

    public ComentarioDto(Comentario comentario) {
        this.id = comentario.getId();
        this.conteudo = comentario.getConteudo();
        this.createdAt = comentario.getCreatedAt();
        this.conta = comentario.getFkConta().getId();
        this.fkTopico = comentario.getFkTopico().getId();
        this.fkComentario = comentario.getFkComentario() == null ? null : comentario.getFkComentario().getId();
        this.comentarios = comentario.getComentarios();
    }

    public int getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getConta() {
        return conta;
    }

    public Integer getFkTopico() {
        return fkTopico;
    }

    public Integer getFkComentario() {
        return fkComentario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }
}