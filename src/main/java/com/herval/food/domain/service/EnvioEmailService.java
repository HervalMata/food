package com.herval.food.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem {

        @Singular
        private Set<String> destinatarios;

        @NotNull
        private String assunto;

        @NotNull
        private String corpo;

        @Singular("variavel")
        private Map<String, Object> variaveis;
    }
}
