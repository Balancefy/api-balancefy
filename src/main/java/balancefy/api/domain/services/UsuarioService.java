package balancefy.api.domain.services;

import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.application.dto.response.UsuarioResponseDto;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.resources.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDto> getUsuarios() throws NotFoundException{
        try {
            List<UsuarioResponseDto> list = usuarioRepository.findAllActiveUsuarioDto();

            if(list.isEmpty()) {
                throw new NotFoundException("Não existe usuários cadastrados");
            }

            return list;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario getUsuarioById(Integer id){
        try {
            return usuarioRepository.findById(id).get();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario create(Usuario usuario) throws AlreadyExistsException {
        try {
            Optional<Usuario> foundUser = usuarioRepository.findByEmail(usuario.getEmail());

            if(foundUser.isEmpty()) {
                usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));

                return usuarioRepository.save(usuario);
            }

            throw new AlreadyExistsException("Email já cadastrado");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario update(Usuario usuario) throws NotFoundException {
        try {
            if(usuarioRepository.existsById(usuario.getId())) {
                return usuarioRepository.save(usuario);
            }

            throw new NotFoundException("Usuário não encontrado");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void delete(Integer id) throws NotFoundException {
        try {
            if(usuarioRepository.existsById(id)) {
                usuarioRepository.deactiveUser(id);
                return;
            }

            throw new NotFoundException("Usuário não encontrado");
        } catch (Exception ex) {
            throw ex;
        }
    }
}
