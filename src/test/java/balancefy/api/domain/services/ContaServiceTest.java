package balancefy.api.domain.services;

import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.TypeUser;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.repositories.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ContaServiceTest {

    @MockBean
    private ContaRepository repository;

    @MockBean
    private ContaService contaService;

    @Test
    void create() throws AlreadyExistsException {
        when(contaService.create(Mockito.any(Conta.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Conta conta = contaService.create(getConta());
        assertTrue(conta.getId() > 0);
    }

    private Conta getConta() {
        Conta c1 = new Conta(1, 1000.00, 0.0, 1, getUsuario());
        return c1;
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

    @Test
    void update() {
    }

    @Test
    void getContaById() {
    }

    @Test
    void delete() {
    }

    @Test
    void updateProgress() {
    }
}