package balancefy.api.entities;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private Double saldo;
    private Double renda;
    private int qtdObjetivosCompletos;
    private Double progresso;
    private boolean autenticado;

//    private List<Movimentacao> movimentacao = new ArrayList<Movimentacao>();
//    private List<Objetivo> objetivos = new ArrayList<Objetivo>();

//    public void registrarMovimentacaoFixa(Movimentacao m){
//        movimentacao.add(m);
//    }

//    public void atualizarMovimentacaoFixa(Movimentacao m, Double valorTotal, String tipo){
//        m.setTipo(tipo);
//        m.setValorTotal(valorTotal);
//    }

//    public void removerMovimentacaoFixa(Movimentacao m){
//        movimentacao.remove(m);
//    }

//    public void adicionarObjetivo(Objetivo o){
//        objetivos.add(o);
//    }

//    public void alterarObjetivo(Objetivo o, Double valorTotal, Double valorInicial){
//        objetivos.setValorTotal(valorTotal);
//        objetivos.setValorInicial(valorInicial);
//    }

//    public void removerObjetivo(Objetivo o){
//        objetivos.remove(o);
//    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public int getQtdObjetivosCompletos() {
        return qtdObjetivosCompletos;
    }

    public void setQtdObjetivosCompletos(int qtdObjetivosCompletos) {
        this.qtdObjetivosCompletos = qtdObjetivosCompletos;
    }

    public Double getProgresso() {
        return progresso;
    }

    public void setProgresso(Double progresso) {
        this.progresso = progresso;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
