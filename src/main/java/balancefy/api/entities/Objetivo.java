package balancefy.api.entities;

import java.util.ArrayList;

public class Objetivo extends Meta {

    private Double valorTotal;
    private Double valorInicial;
    private Integer mesesEstimado;
    private Double pontuacao = 0.0;

    ArrayList<Task> tasks = new ArrayList<>();


    public void criarTasks(){

        for(int i = 0; i < mesesEstimado; i++){
           Task task  = new Task();
           task.setDone(true);

           tasks.add(task);
       }
    }

    public void concluir() {
        for(Task task: tasks) {
            if(!task.getDone()) {
                return;
            }
        }
        setDone(true);
    }

    public Double getValorTotal() {return valorTotal;}

    public void setValorTotal(Double valorTotal) {this.valorTotal = valorTotal;}

    public Double getValorInicial() {return valorInicial;}

    public void setValorInicial(Double valorInicial) {this.valorInicial = valorInicial;}

    public Integer getMesesEstimado() {return mesesEstimado;}

    public void setMesesEstimado(Integer mesesEstimado) {this.mesesEstimado = mesesEstimado;}

}
