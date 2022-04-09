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
}
