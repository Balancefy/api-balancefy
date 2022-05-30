package balancefy.api.domain.services;

import balancefy.api.application.dto.request.ObjetivoDto;
import balancefy.api.application.dto.response.MovimentacaoFixaDto;
import balancefy.api.application.dto.response.ObjetivoContaResponseDto;
import balancefy.api.application.dto.response.ObjetivoResponseDto;
import balancefy.api.application.dto.response.TaskResponseDto;
import balancefy.api.domain.exceptions.AmountException;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.*;
import balancefy.api.resources.entities.keys.TaskObjetivoContaKey;
import balancefy.api.resources.repositories.*;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;


@Service
public class ObjetivoService {

    @Autowired
    ObjetivoContaRepository objetivoContaRepository;

    @Autowired
    ObjetivoRepository objetivoRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    MovimentacaoFixaRepository movimentacaoFixaRepository;

    @Autowired
    TaskObjetivoContaRepository taskObjetivoContaRepository;

    @Autowired
    TaskObjetivoRepository taskObjetivoRepository;

    public ObjetivoConta create(ObjetivoDto objetivoDto, Integer id) throws AmountException, DataFormatException {
        try {
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

            Double valor = calculateValues(newObjetivo);
            newObjetivo = objetivoContaRepository.save(newObjetivo);
            initializeTasks(taskObjetivoRepository.findAllByObjetivoId(newObjetivo.getObjetivo().getId()), newObjetivo, valor);
            return newObjetivo;
        } catch (DataFormatException e) {
            throw e;
        } catch (AmountException e) {
            throw e;
        }

    }

    private Double calculateValues(ObjetivoConta objConta) throws DataFormatException, AmountException {
        Conta conta = objConta.getConta();
        Double valorRestante = 0.0;
        Double valorObjetivo = objConta.getValorTotal() - objConta.getValorInicial();
        Double entradaInicial = conta.getRenda();
        Double savings = 0.0;
        long months = ChronoUnit.MONTHS.between(LocalDate.now(), objConta.getTempoEstimado());

        List<MovimentacaoFixaDto> list = movimentacaoFixaRepository.findAllByFkContaId(conta.getId());

        Double fixedExpenses = list.stream().mapToDouble(MovimentacaoFixaDto::getValor).sum();
        List<TaskObjetivoConta> listTask = taskObjetivoContaRepository.findAllByObjetivoContaIdAndTaskTaskCategoria(conta.getId(), "Economizar");


        if (!listTask.isEmpty()) {
            savings = listTask.stream().mapToDouble(TaskObjetivoConta::getValor).sum();
        }

        valorRestante = fixedExpenses + savings + entradaInicial;

        if (valorRestante < 0) {
            throw new AmountException("Receita insuficiente para planejar mais objetivos");
        }

        if ((valorObjetivo / (valorRestante * 0.3)) > months) {
            throw new DataFormatException("Intervalo de tempo muito curto");
        }

        return valorObjetivo / months;
    }

    public ObjetivoConta accomplish(Integer id) {
        ObjetivoConta objetivo = objetivoContaRepository.getById(id);
        List<TaskObjetivoConta> tasks = taskObjetivoContaRepository.findAllByObjetivoContaId(objetivo.getId());
        tasks.forEach(task -> task.setDone(1));
        taskObjetivoContaRepository.saveAll(tasks);
        objetivo.setDone(1);
        objetivoContaRepository.save(objetivo);

        return objetivo;
    }

    public ObjetivoResponseDto getObjetivoById(Integer id) throws NotFoundException {
        Optional<ObjetivoContaResponseDto> objetivo = objetivoContaRepository.findObjetivoContaById(id);
        if (objetivo.isPresent()) {
            List<TaskObjetivoConta> tasks = taskObjetivoContaRepository.findAllByObjetivoContaId(objetivo.get().getId());
            List<TaskResponseDto> tasksResponse = new ArrayList<>();
            tasks.forEach((it) -> tasksResponse.add(new TaskResponseDto(
                    it.getId(),
                    it.getTask().getOrdem(),
                    it.getDescricao(),
                    it.getDone(),
                    it.getPontuacao(),
                    it.getValor(),
                    it.getCreatedAt()
            )));
            return new ObjetivoResponseDto(objetivo.get(), tasksResponse);
        }
        throw new NotFoundException("Objetivo não encontrado");
    }

    public Double reachOutCurrentGoal(Integer id) throws NotFoundException {
        Optional<ObjetivoConta> objetivo = objetivoContaRepository.findById(id);

        if (objetivo.isPresent()) {
            List<TaskObjetivoConta> tasks = taskObjetivoContaRepository.findAllByObjetivoContaId(objetivo.get().getId());

            Double totalTasks = tasks.stream().filter(it -> it.getDone() == 1).mapToDouble(TaskObjetivoConta::getValor).sum();
            Double goalValue = objetivo.get().getValorTotal() - objetivo.get().getValorInicial();
            return (goalValue - totalTasks);
        }

        throw new NotFoundException("Objetivo não encontrado");
    }


    public List<ObjetivoContaResponseDto> getList(Integer accountId) {
        return objetivoContaRepository.findAllByContaId(accountId).stream().filter(it -> it.getDone() == 0).collect(Collectors.toList());
    }

    private List<TaskObjetivoConta> initializeTasks(List<TaskObjetivo> tasksToInitialize, ObjetivoConta objetivoConta, Double valor) {
        List<TaskObjetivoConta> tasks = new ArrayList<>();
        tasksToInitialize.forEach(taskObjetivo -> tasks.add(new TaskObjetivoConta(
                        new TaskObjetivoContaKey(taskObjetivo.getTask().getId(), objetivoConta.getId()),
                        taskObjetivo,
                        objetivoConta,
                        taskObjetivo.getTask().getCategoria(),
                        0,
                        objetivoConta.getPontuacao() / tasksToInitialize.size(),
                        taskObjetivo.getTask().getId() == 1 ? valor : 0.0
                )
        ));

        return taskObjetivoContaRepository.saveAll(tasks);
    }

    public List<Objetivo> listPreMade() {
        return objetivoRepository.findAll();
    }


}
