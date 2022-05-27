package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.TaskObjetivoConta;
import balancefy.api.resources.entities.keys.TaskObjetivoContaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskObjetivoContaRepository extends JpaRepository<TaskObjetivoConta, TaskObjetivoContaKey> {
    public List<TaskObjetivoConta> findAllByObjetivoContaId(Integer id);
    public List<TaskObjetivoConta> findAllByObjetivoContaIdAndTaskTaskCategoria(Integer id, String categoria);
}
