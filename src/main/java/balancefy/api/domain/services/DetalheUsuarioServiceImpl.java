package balancefy.api.domain.services;

import balancefy.api.application.config.DetalheUsuarioData;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
    private final UsuarioRepository repository;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);
        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }

        return new DetalheUsuarioData(usuario);
    }
}
