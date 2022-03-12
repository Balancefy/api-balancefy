package balancefy.api.entities;

import java.util.ArrayList;

public class Objetivo extends Metas {

    private Double valorTotal;
    private Double valorInicial;
    private Integer mesesEstimado;

    ArrayList<Task> tasks = new ArrayList<>();


    public void CriarTasks(){

        for(int i = 0; i < mesesEstimado; i++){

           Task task  = new Task();

           tasks.add(task);
       }
    }


    public Double getValorTotal() {return valorTotal;}

    public void setValorTotal(Double valorTotal) {this.valorTotal = valorTotal;}

    public Double getValorInicial() {return valorInicial;}

    public void setValorInicial(Double valorInicial) {this.valorInicial = valorInicial;}

    public Integer getMesesEstimado() {return mesesEstimado;}

    public void setMesesEstimado(Integer mesesEstimado) {this.mesesEstimado = mesesEstimado;}

}
