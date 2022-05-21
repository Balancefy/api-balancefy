package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.TaskObjetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskObjetivoRepository extends JpaRepository<TaskObjetivo, Integer> {
    List<TaskObjetivo> findAllByObjetivoId(Integer id);
}
