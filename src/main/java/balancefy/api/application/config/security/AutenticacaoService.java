package balancefy.api.application.config.security;

import balancefy.api.resources.entities.DetalheUser;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public DetalheUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);

        if(usuario.isPresent()) {
            return new DetalheUser(usuario.get());
        }

        throw new UsernameNotFoundException("Dados invalidos!");
    }
}
