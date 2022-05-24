package balancefy.api.application.dto.response;

public class BiggestExpensesDto {
    Double porcentagem;
    String tipo;

    public BiggestExpensesDto(Double porcentagem, String tipo) {
        this.porcentagem = porcentagem;
        this.tipo = tipo;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
