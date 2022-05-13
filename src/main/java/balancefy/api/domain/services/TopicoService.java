package balancefy.api.domain.services;

import balancefy.api.application.dto.response.TopicoResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> getTopico() {
        return topicoRepository.findAll();
    }

    public Topico create(TopicoResponseDto topico) {
        try {
            Conta conta = contaRepository.getById(topico.getId());
            Topico topicoDto = new Topico(topico.getTitulo(), topico.getDescricao(), conta);
            return topicoRepository.save(topicoDto);

        } catch (Exception ex) {
            throw ex;
        }
    }

}
