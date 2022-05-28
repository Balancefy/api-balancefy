package balancefy.api.application.dto.request;

import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;

import javax.validation.constraints.NotBlank;

public class ComentarioRequestDto {

    @NotBlank
    private String conteudo;
    private Integer idTopico;
    private Integer idComentario;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Integer idTopico) {
        this.idTopico = idTopico;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }
}
