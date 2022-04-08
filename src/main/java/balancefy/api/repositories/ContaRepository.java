package balancefy.api.repositories;

import balancefy.api.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Transactional // do pacote org.spring....
    @Modifying
    @Query("update Conta c set c.progresso = ?2 where c.id = ?1")
    void atualizarProgresso(Integer id, double progresso);
}
