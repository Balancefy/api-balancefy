package balancefy.api.repositories;

import balancefy.api.dto.response.DicaResponseDto;
import balancefy.api.entities.Dica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DicaRepository extends JpaRepository<Dica, Integer> {
}
