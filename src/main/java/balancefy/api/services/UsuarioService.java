package balancefy.api.services;

import balancefy.api.dto.request.LoginDto;
import balancefy.api.dto.response.UsuarioResponseDto;
import balancefy.api.entities.Usuario;
import balancefy.api.exceptions.AlreadyExistsException;
import balancefy.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDto> getUsuarios(){
        try {
            return usuarioRepository.findAllActiveUsuarioDto();
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

    public Usuario getUsuarioByLogin(LoginDto loginDto){
        try {
            return usuarioRepository.findByEmailAndSenha(loginDto.getEmail(), loginDto.getSenha());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario create(Usuario usuario) throws AlreadyExistsException {
        try {
            Usuario foundUser = usuarioRepository.findByEmail(usuario.getEmail());

            if(foundUser != null) {
                throw new AlreadyExistsException("Email já cadastrado");
            }

            return usuarioRepository.save(usuario);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario update(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void delete(Integer id) {
        try {
            usuarioRepository.deactiveUser(id);
            return;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
