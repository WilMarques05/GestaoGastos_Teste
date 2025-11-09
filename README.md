# ⚠️ PROJETO DE TESTE CRÍTICO: GESTÃO DE CUSTOS

## 🎯 Objetivo Principal:

Este projeto **NÃO TEM FOCO EM PRODUÇÃO**. 
Seu único objetivo é servir como um ambiente de **TESTE DE DEPLOY E DE DESEMPENHO EM AMBIENTE LIMITADO**.

1.  **Validar comandos de *build* do Maven** em *container* (Docker/Render).
2.  **Testar o *deploy* na plataforma Render** (Plano Gratuito).
3.  Servir como estudo de caso para **Performance com Paginação e Cache**.

---

# 🔴 ATENÇÃO: PERFORMANCE E CÓDIGO (LEITURA OBRIGATÓRIA):

> **ATUALIZAÇÃO IMPORTANTE:** A classe `GestaoDespesasSeeder` foi **comentada** no código. 
A inicialização da aplicação agora é **MUITO MAIS RÁPIDA** (retornando ao comportamento normal de um Spring Boot), 
pois o gargalo de gravar 150.000 registros no banco de dados com 0.1 CPU foi eliminado.

A classe `GestaoDespesasSeeder` foi **comentada**, mas **permanece no código** para referência. 
O banco de dados agora inicia vazio ou com poucos dados, permitindo um *startup* rápido.

## 🐌 EXTREMA LENTIDÃO NA EXECUÇÃO:

> **CAUSA PRINCIPAL:** A aplicação está rodando em um **PLANO GRATUITO DO RENDER** com recursos mínimos.
>
> * **CPU LIMITADA:** O servidor utiliza apenas **0.1 de CPU (100 milicore)**.
> * **CONSEQUÊNCIA:** O processamento interno das requisições e a comunicação com o banco de dados ainda serão **lentos**.

## 🚧 CÓDIGO E BOAS PRÁTICAS NÃO MANTIDAS:

> O código-fonte **NÃO SEGUE AS BOAS PRÁTICAS** de desenvolvimento, arquitetura ou segurança. 
O foco foi na **CELERIDADE** para testar comandos de *build* e *deployment* da aplicação na nuvem.

---

## 💻 URL da Aplicação:

A aplicação está disponível publicamente em:

### **`https://gestaogastos-teste.onrender.com`**

---

## 🛠️ Endpoints de Gerenciamento (`/gestao`):

| Método     | Endpoint Completo | Descrição                                                                  |
| **`POST`** | `/gestao/create`  | **Cria** um novo registro de despesa.                                      |
| **`GET`**  | `/gestao/{email}` | **Consulta** as despesas de um e-mail. Permite filtro opcional por `data`. |

### Exemplo de Requisição (JSON Body)

```json
{
  "descricao": "Nova despesa de teste",
  "data": "2025-11-09",
  "valor": 100.50,
  "email": "willis@teste", 
  "categoria": "Infraestrutura"
}
