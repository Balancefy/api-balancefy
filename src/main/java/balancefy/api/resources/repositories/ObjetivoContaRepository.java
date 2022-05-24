package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.ObjetivoContaResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.ObjetivoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ObjetivoContaRepository extends JpaRepository<ObjetivoConta, Integer> {
    int countByDoneAndConta(Integer done, Conta conta);
    @Query("select new balancefy.api.application.dto.response.ObjetivoContaResponseDto(objetivo) from ObjetivoConta objetivo where objetivo.id = ?1")
    Optional<ObjetivoContaResponseDto> findObjetivoContaById(Integer id);
}
