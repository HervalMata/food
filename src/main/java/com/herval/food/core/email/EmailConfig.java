package com.herval.food.core.email;

import com.herval.food.domain.service.EnvioEmailService;
import com.herval.food.infrastructure.service.email.FakeEnvioEmailService;
import com.herval.food.infrastructure.service.email.SandboxEnvioEmailService;
import com.herval.food.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/*
 * Criado Por Herval Mata em 19/12/2019
 */
@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    public EnvioEmailService envioEmailService() {
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            case SANDBOX:
                return new SandboxEnvioEmailService();
            default:
                return null;
        }
    }
}
