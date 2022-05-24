package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.TaskObjetivoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskObjetivoContaRepository extends JpaRepository<TaskObjetivoConta, Integer> {
    public List<TaskObjetivoConta> findAllByObjetivoContaId(Integer id);
    public List<TaskObjetivoConta> findAllByObjetivoContaIdAndTaskTaskCategoria(Integer id, String categoria);
}
