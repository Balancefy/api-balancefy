package balancefy.api.entities;

import java.sql.Time;
import java.util.ArrayList;

public class Objetivo extends Metas {

    private Double valotTotal;
    private Double valorInicial;
    private Integer mesesEstimado;

    ArrayList<Task> tasks = new ArrayList<>();


    public void CriarTasks(){

        for(int i = 0; i < mesesEstimado; i++){

           Task task  = new Task();

           tasks.add(task);
       }
    }


    public Double getValotTotal() {return valotTotal;}

    public void setValotTotal(Double valotTotal) {this.valotTotal = valotTotal;}

    public Double getValorInicial() {return valorInicial;}

    public void setValorInicial(Double valorInicial) {this.valorInicial = valorInicial;}

    public Integer getMesesEstimado() {return mesesEstimado;}

    public void setMesesEstimado(Integer mesesEstimado) {this.mesesEstimado = mesesEstimado;}

}
