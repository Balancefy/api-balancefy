package balancefy.api.application.dto.request;

import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;

import javax.validation.constraints.NotBlank;

public class ComentarioRequestDto {

    private Integer id;

    @NotBlank
    private String conteudo;

    private Conta fkConta;
    private Topico fkTopico;
    private Comentario fkComentario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Conta getFkConta() {
        return fkConta;
    }

    public void setFkConta(Conta fkConta) {
        this.fkConta = fkConta;
    }

    public Topico getFkTopico() {
        return fkTopico;
    }

    public void setFkTopico(Topico fkTopico) {
        this.fkTopico = fkTopico;
    }

    public Comentario getFkComentario() {
        return fkComentario;
    }

    public void setFkComentario(Comentario fkComentario) {
        this.fkComentario = fkComentario;
    }
}
