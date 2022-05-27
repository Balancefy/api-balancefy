package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {
    List<Topico> findByTituloContains(String titulo);

    List<Topico> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Topico> findTop3ByFkContaIdOrderById(Integer id);

}
