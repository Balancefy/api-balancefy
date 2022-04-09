package balancefy.api.services;

import balancefy.api.dto.response.DicaResponseDto;
import balancefy.api.entities.Dica;
import balancefy.api.entities.Usuario;
import balancefy.api.exceptions.AlreadyExistsException;
import balancefy.api.repositories.DicaRepository;
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
}
