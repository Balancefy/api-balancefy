package balancefy.api.domain.services;

import balancefy.api.resources.entities.Objetivo;
import balancefy.api.resources.repositories.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ObjetivoService {

    @Autowired
    ObjetivoRepository objetivoRepository;


    public Objetivo create() {
        return null;
    }
}
