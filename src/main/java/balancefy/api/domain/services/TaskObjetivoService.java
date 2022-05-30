package balancefy.api.domain.services;

import balancefy.api.application.dto.response.TaskResponseDto;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.resources.entities.Movimentacao;
import balancefy.api.resources.entities.TaskObjetivoConta;
import balancefy.api.resources.entities.keys.TaskObjetivoContaKey;
import balancefy.api.resources.repositories.MovimentacaoRepository;
import balancefy.api.resources.repositories.ObjetivoContaRepository;
import balancefy.api.resources.repositories.TaskObjetivoContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskObjetivoService {

    @Autowired
    TaskObjetivoContaRepository taskObjetivoContaRepository;

    @Autowired
    ObjetivoContaRepository objetivoContaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    public TaskResponseDto accomplishTask(TaskObjetivoContaKey key) throws NotFoundException {
        Optional<TaskObjetivoConta> task = taskObjetivoContaRepository.findById(key);
        if(!task.isPresent()){
            throw new NotFoundException("Task not found");
        }
        task.get().setDone(1);
        movimentacaoRepository.save(new Movimentacao(task.get()));
        TaskObjetivoConta updatedTask = taskObjetivoContaRepository.save(task.get());

        return new TaskResponseDto(
            updatedTask.getId(),
            updatedTask.getTask().getOrdem(),
            updatedTask.getDescricao(),
            updatedTask.getDone(),
            updatedTask.getPontuacao(),
            updatedTask.getValor(),
            updatedTask.getCreatedAt()
        );
    }
}
