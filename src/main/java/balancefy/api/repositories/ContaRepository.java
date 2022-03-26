package balancefy.api.repositories;

import balancefy.api.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ContaRepository extends JpaRepository<Conta, Repository> {
}
