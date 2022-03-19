package balancefy.api.repositories;

import balancefy.api.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Integer> {
}
