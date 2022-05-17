package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.ObjetivoConta;
import balancefy.api.resources.entities.Task;
import balancefy.api.resources.entities.TaskObjetivo;

import java.util.List;

public class ObjetivoResponseDto extends ResponseDto {
    private ObjetivoContaResponseDto objetivo;
    private List<TaskResponseDto> tasks;

    public ObjetivoResponseDto(ObjetivoContaResponseDto objetivo, List<TaskResponseDto> tasks) {
        super("Sucesso");
        this.objetivo = objetivo;
        this.tasks = tasks;
    }

    public ObjetivoResponseDto(Exception ex) {
        super(ex.getMessage());
    }

    public ObjetivoContaResponseDto getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(ObjetivoContaResponseDto objetivo) {
        this.objetivo = objetivo;
    }

    public List<TaskResponseDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponseDto> tasks) {
        this.tasks = tasks;
    }
}
