package com.devwill.gestao_custos.controller;

import com.devwill.gestao_custos.custon_messages.ErrorMensages;
import com.devwill.gestao_custos.entity.Despesa;
import com.devwill.gestao_custos.service.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/gestao")
@RestController
public class GestaoDespesasController {
    private final DespesaService despesaService;

    public GestaoDespesasController(DespesaService despesaService){
        this.despesaService = despesaService;
    }
    /*
    * --- Precisa ser feito:
    * - Criar tabela no banco de dados
    * - Criar entidade
    * */


    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Despesa despesa){
        try {
            var result = despesaService.salvar(despesa);
            return ResponseEntity.ok(result);
        }catch (IllegalArgumentException e){
            var errorMensages = new ErrorMensages(e.getMessage(), "INVALID_PARAMS");
            return ResponseEntity.status(400).body(errorMensages);
        }
    }

    @GetMapping("/{email}")
    public List<Despesa> consultarDespesasPorEmailData(@PathVariable String email, @RequestParam(required = false) LocalDate data){
        return despesaService.buscarPorEmailEData(email, data);
    }
}
