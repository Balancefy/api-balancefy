package balancefy.api.dto.response;

public class DicaResponseDto {
    private Integer id;
    private String titulo;
    private String tema;
    private ContaResponseDto conta;

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTema() {
        return tema;
    }

    public ContaResponseDto getConta() {
        return conta;
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

    public void setConta(ContaResponseDto conta) {
        this.conta = conta;
    }
}
