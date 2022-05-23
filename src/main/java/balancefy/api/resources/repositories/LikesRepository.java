package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Like;
import balancefy.api.resources.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LikesRepository extends JpaRepository<Like, Integer> {
    int countByTopico(Topico topico);
    @Transactional
    void deleteByTopicoAndConta(Topico topico, Conta conta);
}
