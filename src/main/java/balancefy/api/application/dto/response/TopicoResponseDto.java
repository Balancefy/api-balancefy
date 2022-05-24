package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;

import java.time.LocalDateTime;

public class TopicoResponseDto extends ResponseDto {

    private Integer id;
    private String titulo;
    private String descricao;
    private Integer likes;
    private LocalDateTime createdAt;
    private Conta fkConta;

    public TopicoResponseDto(Topico topico, int likes) {
        super("Sucesso");
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.descricao = topico.getConteudo();
        this.likes = likes;
        this.createdAt = topico.getCreatedAt();
        this.fkConta = topico.getFkConta();
    }

    public TopicoResponseDto(Exception ex) {
        super(ex.getMessage());
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer liked) {
        this.likes = liked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
}
