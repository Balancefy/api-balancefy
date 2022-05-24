package balancefy.api.domain.services;

import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.application.dto.response.ExpensesDto;
import balancefy.api.application.dto.response.FeedTopicoResponseDto;
import balancefy.api.application.dto.response.TopicoResponseDto;
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

import java.util.*;
import java.util.stream.Collectors;

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

    public List<FeedTopicoResponseDto> getTopicoFeed(int id) {
        List<Topico> list = topicoRepository.findAll();
        List<FeedTopicoResponseDto> listFeed = new ArrayList<>();

        for(Topico t: list) {
            listFeed.add(
                    new FeedTopicoResponseDto(
                            new TopicoResponseDto(t, getTopicoLikes(t)),
                            isLikedByAccountId(t.getId(), id),
                            t.getFkConta()
                    )
            );
        }

        return listFeed;
    }

    public Topico getTopicoById(int id) {
        return topicoRepository.findById(id).get();
    }

    public List<FeedTopicoResponseDto> getTopicosByTitulo(String titulo, int id) {
        List<Topico> list = topicoRepository.findByTituloContains(titulo);
        List<FeedTopicoResponseDto> listFeed = new ArrayList<>();

        for(Topico t: list) {
            listFeed.add(
                    new FeedTopicoResponseDto(
                            new TopicoResponseDto(t, getTopicoLikes(t)),
                            isLikedByAccountId(t.getId(), id),
                            t.getFkConta()
                    )
            );
        }

        return listFeed;
    }

    public int getTopicoLikes(Topico topico) {
        return likesRepository.countByTopico(topico);
    }

    public List<FeedTopicoResponseDto> getMostLike(int id) {
        List<Topico> list = likesRepository.getTop3Topicos();
        List<FeedTopicoResponseDto> listFeed = new ArrayList<>();

        for(Topico t: list) {
            listFeed.add(
                    new FeedTopicoResponseDto(
                            new TopicoResponseDto(t, getTopicoLikes(t)),
                            isLikedByAccountId(t.getId(), id),
                            t.getFkConta()
                    )
            );
        }

        listFeed = listFeed.stream()
                        .sorted(Comparator.comparing(topico -> topico.getTopico().getLikes()))
                        .collect(Collectors.toList()).subList(0, 2);

        Collections.reverse(listFeed);

        return listFeed;
    }

    public boolean isLikedByAccountId(int topicId, int id) {
        return likesRepository.findById(new LikesKey(topicId, id)).isPresent();
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
