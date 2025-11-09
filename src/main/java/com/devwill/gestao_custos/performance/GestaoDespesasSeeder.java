/*
package com.devwill.gestao_custos.performance;

//Responsável pela simulação de muitas requisições

import com.devwill.gestao_custos.entity.Despesa;
import com.devwill.gestao_custos.repository.DespesaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component //Para subir essa classe junto com a aplicação no momento que ela é inicializada
public class GestaoDespesasSeeder implements CommandLineRunner {

    private DespesaRepository despesaRepository;
    public GestaoDespesasSeeder(DespesaRepository despesaRepository){
        this.despesaRepository = despesaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Despesa> despesas = new ArrayList<>();

        //Testando no log para saber quando iniciou e finalizou
        System.out.println("Iniciando Geração de seed");
        for (int i = 0; i <= 150000; i ++) {
            Despesa despesa = new Despesa();
            despesa.setDescricao("Gastos nº: " + i);
            despesa.setValor(BigDecimal.valueOf(10 + (1 % 50))); //(1 % 50) informar que tem uma variação entre o 1 e 50
            despesa.setData(LocalDate.now().minusDays(i % 30)); //(i % 30) fazer variação do dia 1 até o dia 30
            despesa.setCategoria("Teste");
            despesa.setEmail("willis@teste");

            despesas.add(despesa);
        }
        despesaRepository.saveAll(despesas);
        System.out.println("Finalizou Geração de seed");
    }
}
*/
