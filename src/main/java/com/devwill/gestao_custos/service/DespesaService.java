package com.devwill.gestao_custos.service;

import com.devwill.gestao_custos.entity.Despesa;
import com.devwill.gestao_custos.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository){
        this.despesaRepository = despesaRepository;
    }

    public Despesa salvar(Despesa despesa){
        //Validando se os dados não foram salvos nullos
        if (despesa.getCategoria() == null || despesa.getEmail() == null || despesa.getData() == null ||
                despesa.getValor() == null || despesa.getDescricao() == null){

            throw new IllegalArgumentException("Preencher todos os campos");
        }
        despesa = despesaRepository.save(despesa);
        return despesa;
    }

    public List<Despesa> buscarPorEmailEData(String email, LocalDate data){

        List<Despesa> despesas;
        if (data != null){
            despesas = despesaRepository.findByEmailAndData(email, data);
        }else {
            despesas = despesaRepository.findByEmail(email);
        }
        return despesas;
    }
}
