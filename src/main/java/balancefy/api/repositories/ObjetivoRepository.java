package balancefy.api.repositories;

import balancefy.api.entities.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {
}
