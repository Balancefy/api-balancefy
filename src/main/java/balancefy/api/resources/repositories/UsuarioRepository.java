package balancefy.api.resources.repositories;

import balancefy.api.application.dto.response.UsuarioResponseDto;
import balancefy.api.resources.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.avatar = ?1 where u.id = ?2")
    void updateAvatar(String fileName, Integer id);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.banner = ?1 where u.id = ?2")
    void updateBanner(String fileName, Integer id);
}
