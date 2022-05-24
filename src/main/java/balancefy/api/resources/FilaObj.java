package balancefy.api.resources;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        tamanho = 0;
        fila = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if(isFull()) {
            throw new IllegalStateException("Fila está cheia");
        }

        fila[tamanho++] = info;
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        T first = fila[0];

       if(!isEmpty()) {
           for (int i = 0; i < tamanho - 1; i++) {
               fila[i] = fila[i + 1];
           }

           fila[--tamanho] = null;
       }

        return first;

    }

    public void exibe() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(fila[i]);
        }
    }

    public T[] getFila() {
        return fila;
    }

    public int getTamanho() {
        return tamanho;
    }
}
