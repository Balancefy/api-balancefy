package balancefy.api.application.controller;

import balancefy.api.application.config.security.TokenService;
import balancefy.api.application.dto.request.MovimentacaoFixaRequestDto;
import balancefy.api.application.dto.response.ContaResponseDto;
import balancefy.api.application.dto.response.MovimentacaoFixaDto;
import balancefy.api.domain.exceptions.AlreadyExistsException;
import balancefy.api.domain.exceptions.NotFoundException;
import balancefy.api.domain.services.ContaService;
import balancefy.api.domain.services.MovimentacaoFixaService;
import balancefy.api.resources.FilaObj;
import balancefy.api.resources.PilhaObj;
import balancefy.api.resources.entities.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@RestController
@RequestMapping("/transactionFixed")
public class MovimentacaoFixaController {

    @Autowired
    private MovimentacaoFixaService movimentacaoFixaService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ContaService contaService;

    FilaObj<MovimentacaoFixaRequestDto> fila = new FilaObj<>(500);
    PilhaObj<MovimentacaoFixaRequestDto> pilha = new PilhaObj<>(1500);


    @GetMapping("/{id}")
    public List<MovimentacaoFixaDto> getList(@PathVariable Integer id) {
        return movimentacaoFixaService.getAllMovimentacaoFixa(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody MovimentacaoFixaRequestDto movimentacaoFixa, @RequestHeader(value = "Authorization") String token) {
        try {
            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));

            Conta account = contaService.getContaById(id);
            return ResponseEntity.status(201).body(movimentacaoFixaService.create(movimentacaoFixa, account));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(204).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            movimentacaoFixaService.deleteMovimentacaoFixa(id);
            return ResponseEntity.status(200).build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

//    @PostMapping("/uploadTxt")
//    public ResponseEntity uploadTxt(@RequestParam MultipartFile arquivo,
//                                       @RequestHeader(value = "Authorization") String token) {
//        try {
//            int id = tokenService.getIdUsuario(token.replace("Bearer ", ""));
//            String setIdMovimentacaoFixa;
//            String setValor;
//            String setCategoria;
//            String setDescricao;
//            String setTipo;
//            boolean createTxt = false;
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()));
//            String registro = reader.readLine();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
//            while (registro != null) {
//                String indice = registro.substring(0, 3);
//                if (indice.equals("000")) {
//
//                    setIdMovimentacaoFixa = registro.substring(0, 4); //4
//                    setCategoria = registro.substring(5, 25); //20
//                    setValor = registro.substring(26, 34); //8
//                    setDescricao = registro.substring(35, 85); //50
//                    setTipo = registro.substring(85, 100); //50
//
//                    Double valor = Double.valueOf(setValor);
//                    MovimentacaoFixaRequestDto movimentacaoFixaRequestDto =
//                            new MovimentacaoFixaRequestDto(valor, setCategoria, setDescricao, setTipo);
//
//                    Conta account = contaService.getContaById(id);
//                    movimentacaoFixaService.create(movimentacaoFixaRequestDto, account);
//                    createTxt = true;
//                }
//                registro = reader.readLine();
//            }
//            if (createTxt){
//                return ResponseEntity.created(null).build();
//            }
//            return ResponseEntity.status(500).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return ResponseEntity.badRequest().build();
//    }


}