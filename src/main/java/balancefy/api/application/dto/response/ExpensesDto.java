package balancefy.api.application.dto.response;

public class ExpensesDto {
    String tipo;
    Double totalGasto;

    public ExpensesDto(String tipo, Double totalGasto) {
        this.tipo = tipo;
        this.totalGasto = totalGasto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(Double totalGasto) {
        this.totalGasto = totalGasto;
    }
}
