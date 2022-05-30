package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Comentario;

import java.time.LocalDateTime;
import java.util.List;

public class ComentarioCurtoResponseDto {

    private Integer id;
    private String descricao;
    private LocalDateTime createdAt = LocalDateTime.now();
    private ContaResponseDto fkConta;
    private List<ComentarioCurtoResponseDto> listComentarios;

    public ComentarioCurtoResponseDto(Integer id, String descricao, LocalDateTime createdAt, ContaResponseDto fkConta, List<ComentarioCurtoResponseDto> listComentarios) {
        this.id = id;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.fkConta = fkConta;
        this.listComentarios = listComentarios;
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

    public ContaResponseDto getFkConta() {
        return fkConta;
    }

    public void setFkConta(ContaResponseDto fkConta) {
        this.fkConta = fkConta;
    }

    public List<ComentarioCurtoResponseDto> getListComentarios() {
        return listComentarios;
    }

    public void setListComentarios(List<ComentarioCurtoResponseDto> listComentarios) {
        this.listComentarios = listComentarios;
    }
}
