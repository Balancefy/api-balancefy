package balancefy.api.domain.services;

import balancefy.api.application.dto.response.DicaResponseDto;
import balancefy.api.domain.exceptions.FileException;
import balancefy.api.resources.entities.Dica;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.resources.entities.MovimentacaoFixa;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.repositories.DicaRepository;
import balancefy.api.resources.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
public class DicaService {
    @Autowired
    private DicaRepository dicaRepository;

    public List<DicaResponseDto> getDicas() { return dicaRepository.listAllDicas(); }

    public List<DicaResponseDto> getDicaByTitulo(String titulo){
        return dicaRepository.findByTitulo(titulo);
    }

    public Dica create(Dica dica) throws AlreadyExistsException {
        try {
            return dicaRepository.save(dica);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Dica> getAllDicas() {
        return dicaRepository.findAll();
    }

    public void createCsv(String nomeArq, ListaObj<Dica> lista) throws FileException {
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
                Dica dica = lista.getElemento(i);

                saida.format(
                        "%d,%s,%s,%s\r\n",
                        dica.getId(),
                        dica.getTema(),
                        dica.getDescricao(),
                        dica.getTitulo()
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

    public void exportCsv() throws Exception {
        try {
            List<Dica> dicas = getAllDicas();
            ListaObj<Dica> lista = new ListaObj<>(dicas.size());

            for (Dica d : dicas){
                lista.adiciona(d);
            }

            createCsv("dicas", lista);
        } catch (Exception ex) {
            throw ex;
        }

    }
}
