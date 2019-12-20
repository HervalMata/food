package com.herval.food.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    public void enviar(Mensagem mensagem) {
        String corpo = processarTemplate(mensagem);
        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
