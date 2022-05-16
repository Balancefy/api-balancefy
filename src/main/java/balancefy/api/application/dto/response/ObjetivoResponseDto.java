package balancefy.api.application.dto.response;

import balancefy.api.resources.entities.ObjetivoConta;
import balancefy.api.resources.entities.Task;
import balancefy.api.resources.entities.TaskObjetivo;

import java.util.List;

public class ObjetivoResponseDto {
    private ObjetivoConta objetivo;
    private List<TaskResponseDto> tasks;
}
