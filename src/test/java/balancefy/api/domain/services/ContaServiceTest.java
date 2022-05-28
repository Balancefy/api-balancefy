package balancefy.api.domain.services;

import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.enums.TypeUser;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.ObjetivoContaRepository;
import balancefy.api.resources.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = {ContaService.class})
class ContaServiceTest {

    @MockBean
    private ContaRepository repository;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usurarioRepository;

    @MockBean
    private ObjetivoContaRepository objetivoContaRepository;

    @Autowired
    private ContaService contaService;

    @Test
    void create() throws AlreadyExistsException {


        when(usurarioRepository.findByEmail(getUsuario().getEmail()))
                .thenReturn(Optional.empty());

        when(usuarioService.create(getConta().getFkUsuario()))
                .thenReturn(getUsuario());

        when(contaService.create(getConta()))
                .thenReturn(getContaAtualizada());

        Conta conta = contaService.create(getConta());

        Usuario usuario = usuarioService.create(getUsuario());

        assertTrue(conta.getId() > 0);
    }


    @Test
    void update() throws NotFoundException {

        Conta atualizarConta = new Conta(1, 2000.00, 0.0, 1, getUsuario());;
        atualizarConta.setRenda(1000.0);

        when(repository.existsById(atualizarConta.getId()))
                .thenReturn(true);

        when(contaService.update(atualizarConta))
                .thenReturn(getContaAtualizada());

        Conta conta = contaService.update(atualizarConta);
        assertEquals(conta.getRenda(), getContaAtualizada().getRenda());

    }

    @Test
    void updateInvalid() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());;


        when(repository.existsById(c1.getId()))
                .thenReturn(false);

        assertThrows(
                NotFoundException.class,
                () -> contaService.update(c1)
        );

    }


    @Test
    void getContaById() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());

        when(repository.existsById(1)).thenReturn(true);

        when(repository.findById(1)).thenReturn(Optional.of(c1));
        assertEquals(c1, contaService.getContaById(1));
    }

    @Test
    void getContaByIdInvalid() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());

        when(repository.existsById(1)).thenReturn(false);

        assertThrows(
                NotFoundException.class,
                () -> contaService.getContaById(c1.getId())
        );

    }

    @Test
    void delete() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());;

        when(repository.existsById(c1.getId()))
                .thenReturn(true);


       contaService.delete(c1.getId());

        verify(repository, times(1)).deactiveAccount(c1.getId());

        assertEquals(c1.getStatus(), getConta().getStatus());
    }

    @Test
    void deleteInvalid() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());;

        when(repository.existsById(c1.getId()))
                .thenReturn(false);

        assertThrows(
                NotFoundException.class,
                () -> contaService.delete(c1.getId())
        );
    }


    @Test
    void updateProgress() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());;
        c1.setProgresso(1.0);

        when(repository.existsById(c1.getId()))
                .thenReturn(true);

        Conta conta = contaService.updateProgress(c1.getId(),c1.getProgresso());

        assertEquals(c1.getStatus(), getConta().getStatus());

    }

    @Test
    void updateProgressnvalid() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());;
        c1.setProgresso(1.0);

        when(repository.existsById(c1.getId()))
                .thenReturn(false);

        assertThrows(
                NotFoundException.class,
                () -> contaService.updateProgress(c1.getId(), c1.getProgresso())
        );

    }


    private Usuario getUsuario() {
        Usuario u1 = new Usuario(
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
        return u1;
    }

    private Conta getConta() {
        Conta c1 = new Conta(1, 1000.00, 0.0, 1, getUsuario());
        return c1;
    }

    private Conta getContaAtualizada() {
        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());
        return c1;
    }

}