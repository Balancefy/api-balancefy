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

import java.io.FileWriter;
import java.io.IOException;
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

    public void exportCsv(Integer id) throws Exception {
        try {
            List<MovimentacaoFixa> movimentacoes = movimentacaoFixaRepository.findAllByFkConta(contaRepository.findById(id).get());
            ListaObj<MovimentacaoFixa> lista = new ListaObj<>(movimentacoes.size());

            for(MovimentacaoFixa m: movimentacoes) {
                lista.adiciona(m);
            }

            createCsv("movimentacoes", lista);
        } catch (Exception ex) {
            throw ex;
        }

    }
}
