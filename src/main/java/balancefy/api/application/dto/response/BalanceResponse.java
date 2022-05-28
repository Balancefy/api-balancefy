package balancefy.api.application.dto.response;

public class BalanceResponse extends ResponseDto {
    private double saldo;
    private double entrada;
    private double saida;

    public BalanceResponse(double saldo, double entrada, double saida) {
        super("Sucesso");
        this.saldo = saldo;
        this.entrada = entrada;
        this.saida = saida;
    }

    public BalanceResponse(Exception ex) {
        super(ex.getMessage());
    }

    public double getSaldo() {
        return saldo;
    }

    public double getEntrada() {
        return entrada;
    }

    public double getSaida() {
        return saida;
    }
}