package balancefy.api.application.controller;

import balancefy.api.application.dto.response.TaskResponseDto;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.domain.services.TaskObjetivoService;
import balancefy.api.resources.entities.TaskObjetivoConta;
import balancefy.api.resources.entities.keys.TaskObjetivoContaKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/goals/tasks/")
public class TaskController {

    @Autowired
    TaskObjetivoService taskObjetivoService;

    @PatchMapping
    public ResponseEntity completeTask(@RequestBody TaskObjetivoContaKey id) {
        try {
            return ResponseEntity.status(200).body(taskObjetivoService.accomplishTask(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
