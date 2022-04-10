package balancefy.api.services;

import balancefy.api.dto.response.DicaResponseDto;
import balancefy.api.entities.Dica;
import balancefy.api.entities.Usuario;
import balancefy.api.exceptions.AlreadyExistsException;
import balancefy.api.repositories.DicaRepository;
import balancefy.api.resources.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

    public void createDicaCsv() {
        List<Dica> dicas = getAllDicas();
        ListaObj<Dica> lista = new ListaObj<>(dicas.size());

        for (Dica d : dicas){
            lista.adiciona(d);
        }

        ListaObj.gravaArquivoCsv(lista, "dicas");
    }
}
