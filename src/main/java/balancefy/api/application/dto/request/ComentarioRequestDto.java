package balancefy.api.application.dto.request;

import javax.validation.constraints.NotBlank;

public class ComentarioRequestDto {

    private Integer id;

    @NotBlank
    private String conteudo;

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
}
