package balancefy.api.domain.services;

import balancefy.api.application.dto.response.BalanceResponse;
import balancefy.api.application.dto.response.ContaRankResponseDto;
import balancefy.api.application.dto.response.MovimentacaoFixaDto;
import balancefy.api.application.dto.response.MovimentacaoResponseDto;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.resources.entities.Conta;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Movimentacao;
import balancefy.api.resources.entities.MovimentacaoFixa;
import balancefy.api.resources.entities.Usuario;
import balancefy.api.resources.enums.TypeTransaction;
import balancefy.api.resources.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ObjetivoContaRepository objetivoContaRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MovimentacaoFixaRepository movimentacaoFixaRepository;

    public Conta create(Conta conta) throws AlreadyExistsException {
        try {
            Usuario savedUsuer = usuarioService.create(conta.getFkUsuario());
            conta.setFkUsuario(savedUsuer);
            return contaRepository.save(conta);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Conta update(Conta conta) throws NotFoundException{
        try {
            if (contaRepository.existsById(conta.getId())){
                return contaRepository.save(conta);
            }
            throw new NotFoundException("Conta não encontrada");

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

    public void delete(Integer id) throws NotFoundException{
        try {
            if(contaRepository.existsById(id)) {
                contaRepository.deactiveAccount(id);
                return;
            }

            throw new NotFoundException("Usuário não encontrado");
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Conta updateProgress(Integer id, Double progValue) throws NotFoundException{
        try {
            if (contaRepository.existsById(id)){
                return contaRepository.atualizarProgresso(id, progValue);
            }
            throw new NotFoundException("Conta não encontrada");

        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<ContaRankResponseDto> getRank() {
        try {
            List<Conta> rank = contaRepository.findAllActiveAccount();

            return rank.stream().map(it -> {
                    int objetivosConcluido = objetivoContaRepository.countByDoneAndConta(1, it);
                    return new ContaRankResponseDto(it, objetivosConcluido);
                }).collect(Collectors.toList());

        } catch (Exception ex) {
            throw ex;
        }
    }

    public BalanceResponse getBalance(int id) {
        List<MovimentacaoFixaDto> listMovimentacaoFixa = movimentacaoFixaRepository.findAllByFkContaId(id);
        List<MovimentacaoResponseDto> listMovimentacao = movimentacaoRepository.findAllByFkObjetivoContaId(id);
        double entrada = 0.0, saida = 0.0;

        for(MovimentacaoFixaDto m: listMovimentacaoFixa) {
            if(m.getTipo().equals(TypeTransaction.IN.type)) {
                entrada += m.getValor();
            } else if (m.getTipo().equals(TypeTransaction.OUT.type)){
                saida += (m.getValor() * -1);
            }
        }

        for(MovimentacaoResponseDto m: listMovimentacao) {
            if(m.getTipo().equals(TypeTransaction.IN.type)) {
                entrada += m.getValor();
            } else if (m.getTipo().equals(TypeTransaction.OUT.type)){
                saida += m.getValor();
            }
        }

        return new BalanceResponse((entrada - saida), entrada, saida);
    }
}
