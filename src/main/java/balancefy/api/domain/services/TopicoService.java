package balancefy.api.domain.services;

import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.application.dto.response.TopicoResponseDto;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> getTopico() {
        return topicoRepository.findAll();
    }

    public Topico create(TopicoRequestDto topico, Integer id) {
        try {
            Conta conta = contaRepository.getById(id);
            Topico topicoDto = new Topico(topico.getTitulo(), topico.getConteudo(), conta);
            return topicoRepository.save(topicoDto);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public Topico update(TopicoRequestDto topicoRequest, Integer id) throws NotFoundException {
        try {
            Optional<Topico> topico = topicoRepository.findById(topicoRequest.getId());
            if (topico.isPresent() && topico.get().getFkConta().getId().equals(id)){
                return topicoRepository.updateTopic(topicoRequest.getId(), topicoRequest.getTitulo(), topicoRequest.getConteudo());
            }
            throw new NotFoundException("Tópico não encontrada");

        } catch (Exception ex) {
            throw ex;
        }
    }

}
