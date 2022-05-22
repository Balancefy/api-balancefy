package balancefy.api.domain.services;

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
import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService = new ContaService();

    @Test
    void create() {
        Conta c1 = getConta();
        Mockito.when(contaService.create(c1)).thenReturn(getConta());

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