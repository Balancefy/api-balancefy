package balancefy.api.dto.response;

public class DicaResponseDto {
    private Integer id;
    private String titulo;
    private String tema;
    private String descricao;
    private Integer fk_conta;

    public DicaResponseDto(Integer id, String titulo, String tema, String descricao, Integer fk_conta) {
        this.id = id;
        this.titulo = titulo;
        this.tema = tema;
        this.descricao = descricao;
        this.fk_conta = fk_conta;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTema() {
        return tema;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao(){ return descricao; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFk_conta() {
        return fk_conta;
    }

    public void setFk_conta(Integer fk_conta) {
        this.fk_conta = fk_conta;
    }
}
