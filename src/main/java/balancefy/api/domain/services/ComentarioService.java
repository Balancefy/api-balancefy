package balancefy.api.domain.services;

import balancefy.api.application.dto.request.ComentarioRequestDto;
import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;
import balancefy.api.resources.repositories.ComentarioRepository;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.LikesRepository;
import balancefy.api.resources.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> getComentario() {
        return comentarioRepository.findAll();
    }

    public List<Comentario> getByFkTopico(Topico topico) {
        return comentarioRepository.findByFkTopico(topico);
    }

    public Comentario create(ComentarioRequestDto comentario, Integer idConta, Integer idTopico, Integer idComentario) {
        try {
            Conta conta = contaRepository.getById(idConta);
            Topico topico = topicoRepository.getById(idTopico);
            Comentario comentarioDto = new Comentario(comentario.getConteudo(), conta, topico, null);
            return comentarioRepository.save(comentarioDto);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public Comentario createComentario(ComentarioRequestDto comentario, Integer idConta, Integer idTopico, Integer idComentario) {
        try {
            Conta conta = contaRepository.getById(idConta);
            Topico topico = topicoRepository.getById(idTopico);
            Comentario coment = comentarioRepository.getById(idComentario);
            Comentario comentarioDto = new Comentario(comentario.getConteudo(), conta, topico, coment);
            return comentarioRepository.save(comentarioDto);

        } catch (Exception ex) {
            throw ex;
        }
    }



}
