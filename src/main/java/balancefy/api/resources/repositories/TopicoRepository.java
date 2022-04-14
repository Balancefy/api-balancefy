package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Integer> {
}
