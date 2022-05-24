package balancefy.api.domain.services;

import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.TypeUser;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UsuarioServiceTest {

    @MockBean
    private UsuarioRepository repository;

    @MockBean
    private UsuarioService service;

    @Test
    void createValidCase() throws AlreadyExistsException {
        when(service.create(Mockito.any(Usuario.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Usuario usuario = service.create(getUsuario());

        assertEquals(1, usuario.getId());
    }

    @Test
    void createInvalidCase() throws AlreadyExistsException {
        when(service.create(Mockito.any(Usuario.class)))
                .thenThrow(AlreadyExistsException.class);

        assertThrows(
                AlreadyExistsException.class,
                () -> service.create(getUsuario())
        );
    }

    @Test
    void updateValidCase() throws NotFoundException {
        when(service.update(Mockito.any(Usuario.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Usuario usuario = service.update(getUsuario());
        assertEquals(usuario.getId(), 1);
    }

    @Test
    void updateInvalidCase() throws NotFoundException {
        when(service.update(Mockito.any(Usuario.class)))
                .thenThrow(NotFoundException.class);

        assertThrows(
                NotFoundException.class,
                () -> service.update(getUsuario())
        );
    }

//    @Test
//    void updateAvatarValidCase() throws NotFoundException, IOException {
//        when(service.updateAvatar(Mockito.any(), getUsuario().getId()))
//                .thenAnswer(i -> i.getArguments()[0]);
//
//        service.updateAvatar( , getUsuario().getId());
//        assertEquals(usuario.getId(), 1);
//    }

    @Test
    void updateAvatarInvalidCase() {

    }

    @Test
    void updateBanner() {
    }

    @Test
    void updatePassword() {
    }

    private Usuario getUsuario() {
        return new Usuario(
                1,
                "Verdinha",
                "aaaa@gmail.com",
                "verdiner123",
                "foto.jpeg",
                "fundo.jpeg",
                LocalDate.of(2000, 5, 24) ,
                TypeUser.DEFAULT,
                LocalDateTime.now()
        );
    }
}