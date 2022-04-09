package balancefy.api.repositories;

import balancefy.api.dto.response.DicaResponseDto;
import balancefy.api.entities.Conta;
import balancefy.api.entities.Dica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DicaRepository extends JpaRepository<Dica, Integer> {

    @Query("select new balancefy.api.dto.response.DicaResponseDto(d) FROM Dica d")
    List<DicaResponseDto> listAllDicas();

    @Query("select new balancefy.api.dto.response.DicaResponseDto(d) from Dica d WHERE d.titulo = ?1")
    List<DicaResponseDto> findByTitulo(String titulo);
}
