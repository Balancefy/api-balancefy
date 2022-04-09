package balancefy.api.resources;

import balancefy.api.dto.response.DicaResponseDto;
import balancefy.api.entities.Dica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ListaObj<T> {
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[])  new Object[tamanho];
        nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista está cheia");
        }
        else {
            vetor[nroElem++] = elemento;
        }
    }

    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice (int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return false;
        }

        for (int i = indice; i < nroElem-1; i++) {
            vetor[i] = vetor[i+1];
        }

        nroElem--;
        return true;
    }


    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }


    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("Indice Invalido");
            return null;
        }
        else {
            System.out.println(vetor[indice]);
            return vetor[indice];
        }
    }

    public void limpa() {
        nroElem = 0;
    }

    public static void gravaArquivoCsv(ListaObj<Dica> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        nomeArq += ".csv";
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                DicaResponseDto dicas = new DicaResponseDto(lista.getElemento(i));
                saida.format("%d;%s;%s;%s\n",dicas.getId(),dicas.getTitulo(),
                        dicas.getDescricao(), dicas.getTema());
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void exibirArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        boolean error = false;
        nomeArq += ".csv";

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\n");
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado!");
            System.exit(1);
        }

        try {
            System.out.printf("%6s %-14s %7s\n","CODIGO", "NOME", "NOTA");
            while (entrada.hasNext()) {
                Integer codigo = entrada.nextInt();
                String nome = entrada.next();
                Double nota = entrada.nextDouble();

                System.out.printf("%06d %-14s %7.2f\n", codigo,nome,nota);
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Arquivo com problemas" + ex.getMessage());
            error = true;
        } catch (IllegalStateException ex) {
            System.out.println("Erro na leitura do arquivo");
            error = true;
        } finally {
            entrada.close();

            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                error = true;
            }

            if (error) {
                System.exit(1);
            }
        }
    }
}
