package balancefy.api.repositories;

import balancefy.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("select * from Usuario u where email=?1 and senha=?2")
    public Usuario findByEmailAndPass(String email, String senha);
}
