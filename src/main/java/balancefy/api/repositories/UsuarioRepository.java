package balancefy.api.repositories;

import balancefy.api.dto.response.UsuarioResponseDto;
import balancefy.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("select new balancefy.api.dto.response.UsuarioResponseDto(u) from Usuario u")
    List<UsuarioResponseDto> findAllUsuarioDto();
    Usuario findByEmailAndSenha(String email, String senha);
    Usuario findByEmail(String email);
}
