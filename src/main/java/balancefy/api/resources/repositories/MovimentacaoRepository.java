package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.MovimentacaoResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Movimentacao;
import balancefy.api.resources.entities.MovimentacaoFixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    List<Movimentacao> findAllByFkObjetivoContaConta(Conta conta);
    @Query("select new balancefy.api.application.dto.response.MovimentacaoResponseDto(m) from Movimentacao m where m.fkObjetivoConta.id = ?1")
    List<MovimentacaoResponseDto> findAllByFkObjetivoContaId(Integer id);
}
