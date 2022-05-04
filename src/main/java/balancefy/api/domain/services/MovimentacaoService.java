package balancefy.api.domain.services;

import balancefy.api.domain.exceptions.FileException;
import balancefy.api.resources.ListaObj;
import balancefy.api.resources.entities.Movimentacao;
import balancefy.api.resources.entities.MovimentacaoFixa;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.MovimentacaoFixaRepository;
import balancefy.api.resources.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private MovimentacaoFixaRepository movimentacaoFixaRepository;
    @Autowired
    private ContaRepository contaRepository;

    public void createCsv(String nomeArq, ListaObj<MovimentacaoFixa> lista) throws FileException{
        String message = "";
        FileWriter arq = null;
        Formatter saida = null;
        Boolean error = false;
        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            throw new FileException("Erro ao abrir o arquivo");
        }

        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                MovimentacaoFixa movimentacao = lista.getElemento(i);
                Usuario usuario = movimentacao.getFkConta().getFkUsuario();

                saida.format(
                        "%d,%s,%s,%.2f,%d,%s,%s,%s,%.2f,%s\r\n",
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        movimentacao.getFkConta().getProgresso(),
                        movimentacao.getId(),
                        movimentacao.getCategoria(),
                        movimentacao.getDescricao(),
                        movimentacao.getTipo(),
                        movimentacao.getValor(),
                        movimentacao.getCreatedAt()
                );
            }
        }
        catch (FormatterClosedException erro) {
            message = "Erro ao gravar o arquivo";
            error = true;
        }

        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                message = "Erro ao fechar o arquivo";
                error = true;
            }

            if (error) {
                throw new FileException(message);
            }
        }
    }

    public void createRegister(String registro, String nomeArq) throws FileException {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            throw new FileException("Erro ao abrir o arquivo");
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            throw new FileException("Erro ao gravar o arquivo");
        }
    }

    public void createBody(Movimentacao m, String nome) throws FileException {
        try {
            String body = "02";

            body += String.format("%05d", m.getId());
            body += String.format("%-7s", m.getTipo());
            body += String.format("%-20s", m.getTopico());
            body += String.format("%-50s", m.getDescricao());
            body += String.format("%010.2f", m.getValor());
            body += String.format("%-10s", m.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            body += String.format("%05d", m.getFkObjetivoConta().getId());
            body += String.format("%-20s", m.getFkObjetivoConta().getObjetivo().getCategoria());
            body += String.format("%-50s", m.getFkObjetivoConta().getDescricao());
            body += String.format("%010.2f", m.getFkObjetivoConta().getValorTotal());
            body += String.format("%08.2f", m.getFkObjetivoConta().getValorInicial());
            body += String.format("%02d", m.getFkObjetivoConta().getDone());

            createRegister(body, nome);
        } catch (FileException ex) {
            throw ex;
        }

    }

    public void createTxt(List<Movimentacao> lista, String nomeArq) throws FileException {
        try {
            int registerAmount = 0;
            double transactionAmount = 0;

            String header = "00MOVIMENTACAO";
            header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            header += "01";

            createRegister(header, nomeArq);

            for (Movimentacao m : lista) {
                createBody(m, nomeArq);

                registerAmount++;
                transactionAmount+= m.getValor();
            }

            String trailer = "01";
            trailer += String.format("%05d%08.2f", registerAmount, transactionAmount);
            createRegister(trailer, nomeArq);
        } catch (FileException ex) {
            throw ex;
        }
    }

    public void exportCsv(Integer id) throws Exception {
        try {
            List<Movimentacao> movimentacoes = movimentacaoRepository.findAllByFkObjetivoContaConta(contaRepository.findById(id).get());

            createTxt(movimentacoes, "movimentacoes");
        } catch (Exception ex) {
            throw ex;
        }

    }
}
