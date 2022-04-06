package balancefy.api.services;

import balancefy.api.dto.request.LoginDto;
import balancefy.api.entities.Usuario;
import balancefy.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios(){
        try {
            return usuarioRepository.findAll();
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
            String email = loginDto.getEmail();
            String pass = loginDto.getSenha();
            return usuarioRepository.findByEmailAndPass(email, pass);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Usuario create(Usuario usuario) {
        try {
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
            usuarioRepository.deleteById(id);
            return;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
