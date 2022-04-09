package balancefy.api.services;


import balancefy.api.dto.response.MovimentacaoResponseDto;
import balancefy.api.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    MovimentacaoRepository repository;

    public List<MovimentacaoResponseDto> getByObjetivo(Integer id){
        return repository.findByObjetivo(id);
    }


}
