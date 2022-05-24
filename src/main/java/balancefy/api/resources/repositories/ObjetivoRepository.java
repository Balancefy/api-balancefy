package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {

}
