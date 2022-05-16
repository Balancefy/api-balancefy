package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Movimentacao;
import balancefy.api.resources.entities.MovimentacaoFixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    List<Movimentacao> findAllByFkObjetivoContaConta(Conta conta);
}
