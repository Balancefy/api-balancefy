package balancefy.api.resources;

public class PilhaObj<T> {

    private int topo;
    private T[] pilha;

    public PilhaObj(int tamanho) {
        pilha = (T[]) new Object[tamanho];
        topo = -1;
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == pilha.length -1;
    }

    public void push(T info) {
        if(isFull()) {
            System.out.println("Pilha Cheia");
            return;
        }

        pilha[++topo] = info;
    }

    public T pop() {
        return isEmpty() ? null : pilha[topo--];
    }

    public T peek() {
        return isEmpty() ? null : pilha[topo];
    }

    public void exibe() {
        if(isEmpty()) {
            System.out.println("Pilha Vazia");
            return;
        }

        for(int i = topo; i > -1; i--) {
            System.out.println(pilha[i]);
        }
    }

    public PilhaObj multiPop(int n) {
        if(n > topo) {
            return null;
        }

        PilhaObj aux = new PilhaObj(n);

        for(int i = n; i > 0; i--) {
            aux.push(pop());
        }

        return aux;
    }

    public void multiPush(PilhaObj<T> aux) {
        for(int i = aux.topo; i > -1; i--) {
            push(aux.pop());
        }
    }
}
