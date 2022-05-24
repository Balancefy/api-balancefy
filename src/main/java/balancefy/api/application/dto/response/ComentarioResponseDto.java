package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;

import java.time.LocalDateTime;

public class ComentarioResponseDto extends ResponseDto {

    private Integer id;
    private String descricao;
    private LocalDateTime createdAt;
    private Conta fkConta;
    private Topico fkTopico;
    private Comentario fkComentario;

    public ComentarioResponseDto(Comentario comentario) {
        super("Sucesso");
        this.id = comentario.getId();
        this.descricao = comentario.getConteudo();
        this.createdAt = comentario.getCreatedAt();
        this.fkConta = comentario.getFkConta();
        this.fkTopico = comentario.getFkTopico();
        this.fkComentario = comentario.getFkComentario();
    }

    public ComentarioResponseDto(Exception ex) {
        super(ex.getMessage());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
