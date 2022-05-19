package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.ObjetivoConta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetivoContaRepository extends JpaRepository<ObjetivoConta, Integer> {
    int countByDoneAndConta(Integer done, Conta conta);
}
