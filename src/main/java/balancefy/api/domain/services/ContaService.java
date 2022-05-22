package balancefy.api.domain.services;

import balancefy.api.application.dto.response.ContaRankResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.ObjetivoContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ObjetivoContaRepository objetivoContaRepository;

    public Conta create(Conta conta) {
        try {
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
}
