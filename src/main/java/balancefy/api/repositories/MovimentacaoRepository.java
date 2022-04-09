package balancefy.api.repositories;

import balancefy.api.dto.response.DicaResponseDto;
import balancefy.api.dto.response.MovimentacaoResponseDto;
import balancefy.api.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

    @Query("select new balancefy.api.dto.response.MovimentacaoResponseDto(m) from Movimentacao m WHERE m.fkObjetivo.id = ?1")
    List<MovimentacaoResponseDto> findByObjetivo(Integer id);
}
