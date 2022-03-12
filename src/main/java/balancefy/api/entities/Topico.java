package balancefy.api.entities;

public class Topico {
    private Integer idTopico;
    private String titulo;
    private String descricao;

    public Topico(Integer idTopico, String titulo, String descricao) {
        this.idTopico = idTopico;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Topicos{" +
                "idTopico=" + idTopico +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Integer getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Integer idTopico) {
        this.idTopico = idTopico;
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
}
