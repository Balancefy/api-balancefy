package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.ExpensesDto;
import balancefy.api.application.dto.response.ObjetivoContaResponseDto;
import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Like;
import balancefy.api.resources.entities.Topico;
import balancefy.api.resources.entities.keys.LikesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findById(LikesKey id);
    int countByTopico(Topico topico);
    @Transactional
    void deleteByTopicoAndConta(Topico topico, Conta conta);
    @Query("select new balancefy.api.resources.entities.Topico(t.id, max(t.titulo), max(t.conteudo), max(t.fkConta)) from Like l join l.topico t group by t.id ")
    List<Topico> getTop3Topicos();

}
