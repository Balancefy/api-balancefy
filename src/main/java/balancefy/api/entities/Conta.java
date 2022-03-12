package balancefy.api.entities;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private int idUsuario;
    private int idConta;
    private Double saldo = 0.;
    private Double renda = 0.;
    private int qtdObjetivosCompletos = 0;
    private Double progresso = 0.;



    private List<MovimentacaoFixa> movimentacoesFixas = new ArrayList<>();
    private List<Dica> dicas = new ArrayList<>();
    private List<Objetivo> objetivos = new ArrayList<>();

    public void cadastrarMovimentacaoFixa(MovimentacaoFixa m){
        movimentacoesFixas.add(m);
    }

    public void atualizarMovimentacaoFixa( int indiceMov, MovimentacaoFixa m){
        movimentacoesFixas.set(indiceMov, m);
    }

    public void deletarMovimentacaoFixa(int indiceMov){
        movimentacoesFixas.remove(indiceMov);
    }

    public List<MovimentacaoFixa> listaMovimentacaoFixa(){
        return movimentacoesFixas;
    }

    public void cadastrarObjetivo(Objetivo o){
        objetivos.add(o);
    }

    public void atualizarObjetivo(int indiceObj, Objetivo o){
        objetivos.set(indiceObj, o);
    }

    public void deletarObjetivo(int indiceObj){
        objetivos.remove(indiceObj);
    }

    public List<Objetivo> listarObjetivosPorConta(){
        return objetivos;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

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

}
