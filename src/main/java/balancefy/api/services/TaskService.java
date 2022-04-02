package balancefy.api.services;

import balancefy.api.entities.Dica;
import balancefy.api.entities.Task;
import balancefy.api.repositories.DicaRepository;
import balancefy.api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }
}
