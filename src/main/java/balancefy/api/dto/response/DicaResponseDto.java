package balancefy.api.dto.response;

import balancefy.api.entities.Dica;

public class DicaResponseDto extends ResponseDto {
    private Integer id;
    private String titulo;
    private String tema;
    private String descricao;

    public DicaResponseDto(Dica dica) {
        super("Sucesso");
        this.id = dica.getId();
        this.titulo = dica.getTitulo();
        this.tema = dica.getTema();
        this.descricao = dica.getDescricao();
    }

    public DicaResponseDto(Exception ex) {
        super(ex.getMessage());
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
}
