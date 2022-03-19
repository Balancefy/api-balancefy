package balancefy.api.entities;

public class Task extends Meta {

    private Double qtdDesconto;

    public void concluir() {
       setDone(true);
    }
}
