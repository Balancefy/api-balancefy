package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.ComentarioResponseDto;
import balancefy.api.application.dto.response.TopicoResponseDto;
import balancefy.api.resources.entities.Comentario;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    List<Comentario> findByFkTopicoAndFkComentarioNull(Topico topico);
    int countByFkTopicoAndFkComentarioNull(Topico topico);
}
