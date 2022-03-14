package balancefy.api.entities;

public class Task extends Metas {

    private Double qtdDesconto;

    public void concluir() {
       setDone(true);
    }
}
