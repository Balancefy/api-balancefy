package balancefy.api.domain.services;

import balancefy.api.application.dto.request.ObjetivoDto;
import balancefy.api.application.dto.response.ObjetivoContaResponseDto;
import balancefy.api.application.dto.response.ObjetivoResponseDto;
import balancefy.api.application.dto.response.TaskResponseDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.ObjetivoConta;
import balancefy.api.resources.entities.TaskObjetivo;
import balancefy.api.resources.entities.TaskObjetivoConta;
import balancefy.api.resources.entities.keys.TaskObjetivoContaKey;
import balancefy.api.resources.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ObjetivoService {

    @Autowired
    ObjetivoContaRepository objetivoRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    TaskObjetivoContaRepository taskObjetivoContaRepository;

    @Autowired
    TaskObjetivoRepository taskObjetivoRepository;


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

         newObjetivo = objetivoRepository.save(newObjetivo);
         initializeTasks(taskObjetivoRepository.findAllByObjetivoId(newObjetivo.getObjetivo().getId()), newObjetivo);

        return newObjetivo;
    }

    public ObjetivoConta accomplish(Integer id) {
        ObjetivoConta objetivo = objetivoRepository.getById(id);
        List<TaskObjetivoConta >tasks = taskObjetivoContaRepository.findAllByObjetivoContaId(objetivo.getId());
        tasks.forEach(task -> task.setDone(1));
        taskObjetivoContaRepository.saveAll(tasks);
        objetivo.setDone(1);
        objetivoRepository.save(objetivo);

        return objetivo;
    }

    public ObjetivoResponseDto getObjetivoById(Integer id) {
        ObjetivoContaResponseDto objetivo = objetivoRepository.findObjetivoContaById(id);
        List<TaskObjetivoConta> tasks = taskObjetivoContaRepository.findAllByObjetivoContaId(objetivo.getId());
        List<TaskResponseDto> tasksResponse = new ArrayList<>();
        tasks.forEach((it) -> tasksResponse.add(new TaskResponseDto(
                it.getId(),
                it.getTask().getOrdem(),
                it.getDescricao(),
                it.getDone(),
                it.getPontuacao()
        )));

        return new ObjetivoResponseDto(objetivo, tasksResponse);
    }


    private List<TaskObjetivoConta> initializeTasks(List<TaskObjetivo> tasksToInitialize, ObjetivoConta objetivoConta) {
        List<TaskObjetivoConta> tasks =  new ArrayList<>();
        tasksToInitialize.forEach(taskObjetivo -> tasks.add(new TaskObjetivoConta(
                new TaskObjetivoContaKey(taskObjetivo.getTask().getId(), objetivoConta.getId()),
                taskObjetivo,
                objetivoConta,
                taskObjetivo.getTask().getCategoria(),
                0,
                objetivoConta.getPontuacao()
                )
        ));

       return taskObjetivoContaRepository.saveAll(tasks);
    }


}
