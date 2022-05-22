package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.TopicoResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {
    @Query("select new balancefy.api.application.dto.response.TopicoResponseDto(t) from Topico t WHERE t.titulo = ?1")
    List<TopicoResponseDto> findByTitulo(String titulo);

    List<Topico> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Topico> findAllByOrderByLikedDesc();

    List<Topico> findByFkConta(Conta conta);
}
