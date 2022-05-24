package balancefy.api.domain.services;

import balancefy.api.application.dto.request.UsuarioSenhaRequestDto;
import balancefy.api.application.utils.FileUploadUtil;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.TypeUser;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaRepository contaRepository;

    public Usuario create(Usuario usuario) throws AlreadyExistsException {
        try {
            Optional<Usuario> foundUser = usuarioRepository.findByEmail(usuario.getEmail());
            if(foundUser.isEmpty()) {
                if(usuario.getTipo() == TypeUser.DEFAULT) {
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
            if (usuarioRepository.existsById(usuario.getId())) {
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
            if (usuarioRepository.existsById(id)) {
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
            if (usuarioRepository.existsById(id)) {
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

    public void updatePassword(Integer id, UsuarioSenhaRequestDto request) throws IOException, NotFoundException {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()) {
                if (BCrypt.checkpw(request.getSenhaAtual(), usuario.get().getSenha())) {
                    usuario.get().setSenha(BCrypt.hashpw(request.getNovaSenha(), BCrypt.gensalt()));
                    usuarioRepository.save(usuario.get());
                    return;
                } else {
                    throw new NotFoundException("Senha Invalida");
                }
            }

            throw new NotFoundException("Usuário não encontrado");
        } catch (Exception ex) {
            throw ex;
        }
    }
}
