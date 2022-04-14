package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
