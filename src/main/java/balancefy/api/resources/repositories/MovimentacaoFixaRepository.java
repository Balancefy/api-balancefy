package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.MovimentacaoFixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoFixaRepository extends JpaRepository<MovimentacaoFixa, Integer> {
    List<MovimentacaoFixa> findAllByFkConta(Conta conta);
}
