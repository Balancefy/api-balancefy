package balancefy.api.domain.services;

import balancefy.api.application.dto.request.ObjetivoDto;
import balancefy.api.application.dto.response.ObjetivoResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.ObjetivoConta;
import balancefy.api.resources.repositories.ContaRepository;
import balancefy.api.resources.repositories.MovimentacaoRepository;
import balancefy.api.resources.repositories.ObjetivoContaRepository;
import balancefy.api.resources.repositories.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ObjetivoService {

    @Autowired
    ObjetivoContaRepository objetivoRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;


    public ObjetivoConta create(ObjetivoDto objetivoDto, Integer id) {
        Conta conta = contaRepository.getById(id);

        Double pontuacao = (conta.getRenda() * 0.3) + 10000;

        ObjetivoConta newObjetivo = new ObjetivoConta(
                conta,
                objetivoDto.getObjetivo(),
                objetivoDto.getDescricao(),
                0,
                objetivoDto.getValorTotal(),
                objetivoDto.getValorInicial(),
                objetivoDto.getTempoEstimado(),
                pontuacao
        );

        objetivoRepository.save(newObjetivo);
        return newObjetivo;
    }

    public ObjetivoConta accomplish(Integer id){
        ObjetivoConta objetivo = objetivoRepository.getById(id);
        objetivo.setDone(1);
        objetivoRepository.save(objetivo);

        return objetivo;
    }

    public List<ObjetivoResponseDto> getObjetivoByConta(Integer id){
        return null;
    }
}
