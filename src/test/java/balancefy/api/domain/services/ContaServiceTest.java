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
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration

class ContaServiceTest {

    @Autowired
    private ContaService contaService;

    @Test
    void create() throws AlreadyExistsException {

        Conta contaTeste = new Conta(6, 2000.00, 0.0, 1, getUsuario2());;

        contaService.create(contaTeste);

        assertTrue(contaTeste.getId() > 0);
    }

    @Test
    void createInvalid() throws AlreadyExistsException {

        Conta contaTeste = new Conta(1, 2000.00, 0.0, 1, getUsuario());;

        assertThrows(
                AlreadyExistsException.class,
                () -> contaService.create(contaTeste)
        );
    }


    @Test
    void update() throws NotFoundException {

        Conta atualizarConta = new Conta(1, 1000.00, 0.0, 1, getUsuario());;
        atualizarConta.setRenda(2000.0);

        contaService.update(atualizarConta);

        assertEquals(atualizarConta.getRenda(), getContaAtualizada().getRenda());

    }

    @Test
    void updateInvalid() throws NotFoundException {

      Conta conta = new Conta(5, 1000.00, 0.0, 1, getUsuario());

        assertThrows(
                NotFoundException.class,
                () -> contaService.update(conta));

    }


    @Test
    void getContaById() throws NotFoundException {

        Conta c1 = new Conta(1, 1000.00, 0.0, 1, getUsuario());

        Conta conta = contaService.getContaById(c1.getId());

        assertEquals(c1.getFkUsuario().getNome(), conta.getFkUsuario().getNome());
    }

    @Test
    void getContaByIdInvalid() throws NotFoundException {

        Conta c1 = new Conta(5, 1000.00, 0.0, 1, getUsuario());
        assertThrows(
                NotFoundException.class,
                () -> contaService.getContaById(c1.getId())
        );

    }

    @Test
    void delete() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());
        Integer c1Id = c1.getId();


       contaService.delete(c1Id);

        assertEquals(c1.getStatus(), getConta().getStatus());
    }

    @Test
    void deleteInvalid() throws NotFoundException {

        Conta c1 = new Conta(5, 2000.00, 0.0, 1, getUsuario());

        assertThrows(
                NotFoundException.class,
                () -> contaService.delete(c1.getId())
        );
    }


    @Test
    void updateProgress() throws NotFoundException {

        Conta c1 = new Conta(1, 2000.00, 0.0, 1, getUsuario());;
        c1.setProgresso(1.0);

        Conta conta =  contaService.updateProgress(c1.getId(),c1.getProgresso());

        assertEquals(c1.getProgresso(), conta.getProgresso());

    }

    @Test
    void updateProgressnvalid() throws NotFoundException {

        Conta c1 = new Conta(5, 2000.00, 0.0, 1, getUsuario());;
        c1.setProgresso(1.0);


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


    private Usuario getUsuario2() {
        byte [] random = new byte[7];
        new Random().nextBytes(random);
        Usuario u1 = new Usuario(
                1,
                "Verdinha",
                new String(random, Charset.forName("utf-8"))+"@gmail.com",
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