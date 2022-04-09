package balancefy.api.repositories;

import balancefy.api.dto.response.UsuarioResponseDto;
import balancefy.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("select new balancefy.api.dto.response.UsuarioResponseDto(u) from Usuario u WHERE u.status = 1")
    List<UsuarioResponseDto> findAllActiveUsuarioDto();

    Usuario findByEmailAndSenha(String email, String senha);
    Usuario findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.status = 0 where u.id = ?1")
    void deactiveUser(Integer idUsuario);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.avatar =?2 where u.id = ?1")
    void updateAvatar(Integer idUsuario, String avatar);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.senha =?2 where u.id = ?1")
    void updateSenha(Integer idUsuario, String pass);

    @Transactional
    @Modifying
    @Query("update Usuario u set u.banner =?2 where u.id = ?1")
    void updateBanner(int id, String banner);
}
