package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.TaskObjetivoConta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskObjetivoContaRepository extends JpaRepository<TaskObjetivoConta, Integer> {

    public List<TaskObjetivoConta> findAllByObjetivoContaId(Integer id);
}
