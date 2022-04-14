package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Transactional // do pacote org.spring....
    @Modifying
    @Query("update Conta c set c.progresso = ?2 where c.id = ?1")
    Conta atualizarProgresso(Integer id, double progresso);

    @Transactional
    @Modifying
    @Query("update Conta c set c.status = 0 where c.id = ?1")
    void deactiveAccount(Integer idConta);
}
