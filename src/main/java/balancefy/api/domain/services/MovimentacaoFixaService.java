package balancefy.api.domain.services;

import balancefy.api.application.dto.request.MovimentacaoFixaRequestDto;
import balancefy.api.application.dto.response.MovimentacaoFixaDto;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.MovimentacaoFixa;
import balancefy.api.resources.enums.TypeTransaction;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.MovimentacaoFixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoFixaService {

    @Autowired
    private MovimentacaoFixaRepository movimentacaoFixaRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<MovimentacaoFixaDto> getAllMovimentacaoFixa(Integer id) {
        return movimentacaoFixaRepository.findAllByFkContaId(id);
    }

    public MovimentacaoFixa create(MovimentacaoFixaRequestDto movimentacaoFixa, Conta conta) throws AlreadyExistsException {
        try {
            MovimentacaoFixa mov = new MovimentacaoFixa(movimentacaoFixa);
            mov.setTipo(movimentacaoFixa.getValor() < 0 ? TypeTransaction.OUT.type : TypeTransaction.IN.type);
            mov.setFkConta(conta);
            return movimentacaoFixaRepository.save(mov);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void deleteMovimentacaoFixa(Integer id) throws NotFoundException {
        try {
            if (movimentacaoFixaRepository.existsById(id)) {
                movimentacaoFixaRepository.delete(movimentacaoFixaRepository.getById(id));
                return;
            }
            throw new NotFoundException("Movimentação fixa não encontrada");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Conta getContaById(Integer id) throws NotFoundException {
        try {
            if (contaRepository.existsById(id)){
                return contaRepository.findById(id).get();
            }

            throw new NotFoundException("Conta não encontrada");
        } catch (Exception ex) {
            throw ex;
        }
    }
}

