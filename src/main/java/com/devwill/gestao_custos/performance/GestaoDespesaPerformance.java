package com.devwill.gestao_custos.performance;

import com.devwill.gestao_custos.entity.Despesa;
import com.devwill.gestao_custos.repository.DespesaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Classe para testar a performance da aplicação
//Estamos utilizando paginação nessa classe
//Nesta classe vamos utilizar conceitos de cach
// Cache são dados que ficam na memoria da aplicao, ex: pela primeira vez buscamos os dados no banco, já na segunda vez esses dados já estão em cache e ai alem de ir no banco, pegamos os dados do cache
@RequestMapping("gestao/perfomance")
@RestController
@EnableCaching // Habilitando o cache, mas pode ser colocado na classe de inicialização da aplicação, onde tem o metod0 main
public class GestaoDespesaPerformance {

    private DespesaRepository despesaRepository;

    public GestaoDespesaPerformance(DespesaRepository despesaRepository){
        this.despesaRepository = despesaRepository;
    }

    //Testando perfomance sem paginação
    @GetMapping("/sem-paginacao")
    public ResponseEntity<List<Despesa>> listarSemPaginacao(){
        long inicio = System.currentTimeMillis(); //Pegando o tempo de inicio para comparação
        var despesas = despesaRepository.findAll();

        long fim = System.currentTimeMillis(); // Pegando o fim da execução para comparação
        System.out.println("Tempo sem Paginação: " + (fim - inicio) + " ms"); // Iniciando a comparação do tempo que levou para pegar os dados
        return ResponseEntity.ok(despesas);
    }

    //Testando performance com paginação
    @GetMapping("/com-paginacao")
    public ResponseEntity<Page<Despesa>> listarComPaginacao(Pageable pageable){
        StopWatch stopWatch = new StopWatch(); // Classe do Spring para gerenciar tempo, funciona como o System.currentTimeMillis()
        stopWatch.start();

        var despesas = despesaRepository.findAll(pageable);
        stopWatch.stop();

        System.out.println("Tempo com paginação: " + stopWatch.getTotalTimeMillis() + " ms");
        return  ResponseEntity.ok(despesas);
    }

    //Retornando por email
    @GetMapping("/com-paginacao/{email}")
    public ResponseEntity<Page<Despesa>> listarComPaginacaoEmail(@PathVariable String email, Pageable pageable){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var despesas = despesaRepository.findByEmail(email, pageable);
        stopWatch.stop();
        System.out.println("Tempo com paginação: " + stopWatch.getTotalTimeMillis() + " ms");

        return ResponseEntity.ok(despesas);
    }

    @Cacheable(value = "gastosPorEmailCache", key = "#email + '-' + #pageable.pageNumber + '-' +#pageable.pageSize + '-'") //Anotação para utilizar o cache, passando um nome que é o value + a chave para busca, que nesse caso tem o email, o numero de paginas da requisição, e a quantidade de arquivos
    @GetMapping("/cache/{email}")
    public ResponseEntity<Page<Despesa>> buscarPorEmailCache(@PathVariable String email, Pageable pageable){
        var despesas = despesaRepository.findByEmail(email, pageable);

        return ResponseEntity.ok(despesas);
    }

}
