package balancefy.api.application.dto.response;


import java.time.LocalDateTime;

public class TaskResponseDto {
    private Integer id;
    private Integer ordem;
    private String descricao;
    private Integer done;
    private Double pontuacao;
    private LocalDateTime createdAt;

    public TaskResponseDto(Integer id, Integer ordem, String descricao, Integer done, Double pontuacao) {
        this.id = id;
        this.ordem = ordem;
        this.descricao = descricao;
        this.done = done;
        this.pontuacao = pontuacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
