package balancefy.api.controller;

import balancefy.api.dto.response.DicaResponseDto;
import balancefy.api.entities.Dica;
import balancefy.api.entities.Usuario;
import balancefy.api.repositories.DicaRepository;
import balancefy.api.services.DicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dicas")
public class DicaController {

    @Autowired
    private DicaService dicaService;


    @GetMapping
    public @ResponseBody Iterable<DicaResponseDto> getAllDicas() {
        return dicaService.getDicas();
    }

    @PostMapping()
    public ResponseEntity criarDica(){

        return ResponseEntity.status(200).build();
    }

}