package balancefy.api.services;

import balancefy.api.entities.Conta;
import balancefy.api.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public Conta create(Conta conta) {
        try {
            return contaRepository.save(conta);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Conta update(Conta conta) {
        try {
            return contaRepository.save(conta);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Conta getContaById(Integer id){
        try {
            return contaRepository.findById(id).get();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void delete(Integer id) {
        try {
            contaRepository.deleteById(id);
            return;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
