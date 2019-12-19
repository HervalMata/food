package com.herval.food.core.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
@Getter
@Setter
@Component
@ConfigurationProperties("food.storage")
public class StorageProperties {

    private Local local = new Local();

    @Getter
    @Setter
    private class Local {
        private Path diretorioFotos;
    }
}
