package balancefy.api.domain.services;

import balancefy.api.application.dto.request.TopicoRequestDto;
import balancefy.api.resources.entities.Conta;
import balancefy.api.resources.entities.Topico;
import balancefy.api.resources.repositories.ComentarioRepository;
import balancefy.api.resources.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ComentarioRepository repository;


}
