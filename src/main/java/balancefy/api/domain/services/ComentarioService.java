package balancefy.api.domain.services;

import balancefy.api.application.dto.request.ComentarioRequestDto;
import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.application.dto.response.ComentarioCurtoResponseDto;
import balancefy.api.application.dto.response.ComentarioDto;
import balancefy.api.application.dto.response.ComentarioResponseDto;
import balancefy.api.application.dto.response.ContaResponseDto;
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

import java.util.ArrayList;
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

    public List<ComentarioCurtoResponseDto> getByFkTopico(Topico topico) {
        List<Comentario> comentarios = comentarioRepository.findByFkTopicoAndFkComentarioNull(topico);
        return changeComentario(comentarios);
    }

    public List<ComentarioCurtoResponseDto> changeComentario(List<Comentario> comentarios) {
        List<ComentarioCurtoResponseDto> comentariosCurtos = new ArrayList<>();
        for(Comentario c: comentarios) {
            comentariosCurtos.add(
                    new ComentarioCurtoResponseDto(c.getId(), c.getConteudo(), c.getCreatedAt(), new ContaResponseDto(c.getFkConta()), changeComentario(c.getComentarios()))
            );
        }
        return comentariosCurtos;
    }

    public ComentarioDto create(ComentarioRequestDto comentario, Integer idConta) throws NotFoundException {
        try {
            Conta conta = contaRepository.getById(idConta);
            Topico topico = topicoRepository.getById(comentario.getIdTopico());
            Comentario saveComentario = null;

            if(comentario.getIdComentario() != null) {
                if(comentarioRepository.existsById(comentario.getIdComentario())) {
                    saveComentario = comentarioRepository.getById(comentario.getIdComentario());
                } else {
                    throw new NotFoundException("Comentário não existente");
                }
            }

            Comentario comentarioDto = new Comentario(comentario.getConteudo(), conta, topico, saveComentario);

            return new ComentarioDto(comentarioRepository.save(comentarioDto));

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
