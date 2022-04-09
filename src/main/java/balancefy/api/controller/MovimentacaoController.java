package balancefy.api.controller;

import balancefy.api.dto.response.ListaMovimentacaoResponseDto;
import balancefy.api.dto.response.ListaMovimentacaoResponseDto;
import balancefy.api.dto.response.MovimentacaoResponseDto;
import balancefy.api.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
    
    @Autowired
    MovimentacaoService service;
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ListaMovimentacaoResponseDto> getByObjetivo(@PathVariable Integer id){
        try {
            return ResponseEntity.status(200).body(new ListaMovimentacaoResponseDto(service.getByObjetivo(id)));
        }
        catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(new ListaMovimentacaoResponseDto(ex));
        }
        catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(new ListaMovimentacaoResponseDto(ex));
        }
        catch (Exception ex) {
            return ResponseEntity.status(400).body(new ListaMovimentacaoResponseDto(ex));
        }
    }

}
