package balancefy.api.domain.services;

import balancefy.api.application.utils.FileUploadUtil;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.application.dto.response.UsuarioResponseDto;
import balancefy.api.resources.entities.TypeUser;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.resources.repositories.UsuarioRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
                if(usuario.getType() == TypeUser.DEFAULT) {
                    usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
                }

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
                usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));

                return usuarioRepository.save(usuario);
            }

            throw new NotFoundException("Usuário não encontrado");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void updateAvatar(MultipartFile multipartFile, Integer id) throws IOException, NotFoundException {
        try {
            if(usuarioRepository.existsById(id)) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                usuarioRepository.updateAvatar(fileName, id);

                String uploadDir = "user-photos/" + id + "/avatar";

                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

                return;
            }

            throw new NotFoundException("Usuário não encontrado");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void updateBanner(MultipartFile multipartFile, Integer id) throws IOException, NotFoundException {
        try {
            if(usuarioRepository.existsById(id)) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                usuarioRepository.updateBanner(fileName, id);

                String uploadDir = "user-photos/" + id + "/banner";

                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

                return;
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