package balancefy.api.application.dto.request;

import javax.validation.constraints.NotBlank;

public class TopicoRequestDto {

    private Integer id;

    @NotBlank
    private String titulo;
    @NotBlank
    private String conteudo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
