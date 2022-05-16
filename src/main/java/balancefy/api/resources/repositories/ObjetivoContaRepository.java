package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.ObjetivoContaResponseDto;
import balancefy.api.application.dto.response.ObjetivoResponseDto;
import balancefy.api.resources.entities.ObjetivoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ObjetivoContaRepository extends JpaRepository<ObjetivoConta, Integer> {

    @Query("select new balancefy.api.application.dto.response.ObjetivoContaResponseDto(objetivo) from ObjetivoConta objetivo where objetivo.id = ?1")
    ObjetivoContaResponseDto findObjetivoContaById(Integer id);
}
