package balancefy.api.resources.repositories;

import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Like;
import balancefy.api.resources.entities.Topico;
import balancefy.api.resources.entities.keys.LikesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Like, Integer> {
    Optional<Like> findById(LikesKey id);
    int countByTopico(Topico topico);
    @Transactional
    void deleteByTopicoAndConta(Topico topico, Conta conta);
}
