package balancefy.api.repositories;

import balancefy.api.entities.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaRepository extends JpaRepository<Meta, Integer > {
}
