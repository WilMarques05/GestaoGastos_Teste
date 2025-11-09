package com.devwill.gestao_custos.custon_messages;

import lombok.Data;
import org.springframework.http.converter.json.GsonBuilderUtils;

@Data
public class ErrorMensages {
    private String mensagem;
    private String type;

    public ErrorMensages(String mensagem, String type){
        this.mensagem = mensagem;
        this.type = type;
    }
}
