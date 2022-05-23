package balancefy.api.domain.services;

import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Like;
import balancefy.api.resources.entities.Topico;
import balancefy.api.resources.entities.keys.LikesKey;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.LikesRepository;
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

    @Autowired
    private LikesRepository likesRepository;

    public List<Topico> getTopico() {
        return topicoRepository.findAll();
    }

    public Topico getTopicoById(int id) {
        return topicoRepository.findById(id).get();
    }

    public List<Topico> getTopicosByTitulo(String titulo) {
        return topicoRepository.findByTituloContains(titulo);
    }

    public int getTopicoLikes(Topico topico) {
        return likesRepository.countByTopico(topico);
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
                Topico presentTopic = topico.get();
                presentTopic.setConteudo(topicoRequest.getConteudo());
                presentTopic.setTitulo(topicoRequest.getTitulo());

                return topicoRepository.save(presentTopic);
            }

            throw new NotFoundException("Tópico não encontrada");

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void addLike(Integer topicId, Integer accountId) throws NotFoundException {
        try {
            Optional<Topico> topico = topicoRepository.findById(topicId);
            if (topico.isPresent()) {
                Topico presentTopic = topico.get();

                likesRepository.save(
                        new Like(
                                new LikesKey(presentTopic.getId(), accountId),
                                presentTopic,
                                contaRepository.findById(accountId).get()
                        )
                );
                return;
            }

            throw new NotFoundException("Tópico não encontrado");

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void removeLike(Integer id, Integer accountId) throws NotFoundException {
        try {
            Optional<Topico> topico = topicoRepository.findById(id);
            if (topico.isPresent()) {
                Topico presentTopic = topico.get();

                likesRepository.deleteByTopicoAndConta(presentTopic, contaRepository.findById(accountId).get());
                return;
            }
            throw new NotFoundException("Tópico não encontrada");

        } catch (Exception ex) {
            throw ex;
        }
    }

}
