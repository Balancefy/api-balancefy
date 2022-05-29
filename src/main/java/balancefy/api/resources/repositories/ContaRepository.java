package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    @Query("select c from Conta c WHERE c.status = 1 order by c.progresso desc")
    List<Conta> findAllActiveAccount();

    @Transactional
    @Modifying
    @Query("update Conta c set c.progresso = ?2 where c.id = ?1")
    void atualizarProgresso(Integer id, double progresso);

    @Transactional
    @Modifying
    @Query("update Conta c set c.status = 0 where c.id = ?1")
    void deactiveAccount(Integer idConta);
}
