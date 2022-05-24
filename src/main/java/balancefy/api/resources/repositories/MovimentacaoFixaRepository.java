package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.MovimentacaoFixaDto;
import balancefy.api.resources.entities.MovimentacaoFixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoFixaRepository extends JpaRepository<MovimentacaoFixa, Integer> {
    List<MovimentacaoFixaDto> findAllByFkContaId(Integer id);

}
