package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.ExpensesDto;
import balancefy.api.application.dto.response.MovimentacaoResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    List<Movimentacao> findAllByFkObjetivoContaConta(Conta conta);
    @Query("select new balancefy.api.application.dto.response.MovimentacaoResponseDto(m) from Movimentacao m where m.fkObjetivoConta.id = ?1")
    List<MovimentacaoResponseDto> findAllByFkObjetivoContaId(Integer id);
    @Query("select new balancefy.api.application.dto.response.ExpensesDto(m.topico, sum(m.valor))from Movimentacao m where m.fkObjetivoConta.id = ?1 group by m.topico")
    List<ExpensesDto> getExpensesByObjetivo(Integer id);
}
