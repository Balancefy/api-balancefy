package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.DicaResponseDto;
import balancefy.api.resources.entities.Dica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DicaRepository extends JpaRepository<Dica, Integer> {

    @Query("select new balancefy.api.application.dto.response.DicaResponseDto(d) FROM Dica d")
    List<DicaResponseDto> listAllDicas();

    @Query("select new balancefy.api.application.dto.response.DicaResponseDto(d) from Dica d WHERE d.tema = ?1")
    List<DicaResponseDto> findByTitulo(String titulo);
}
