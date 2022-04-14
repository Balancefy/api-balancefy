package balancefy.api.application.controller;

import balancefy.api.resources.entities.Task;
import balancefy.api.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public @ResponseBody Iterable<Task> getAllTask() {
        return taskService.getAllTask();
    }


}
