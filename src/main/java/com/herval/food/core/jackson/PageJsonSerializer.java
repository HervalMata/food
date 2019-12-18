package com.herval.food.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("content", page.getContent());
        gen.writeObjectField("size", page.getSize());
        gen.writeObjectField("totalElements", page.getTotalElements());
        gen.writeObjectField("totalPages", page.getTotalPages());
        gen.writeObjectField("number", page.getNumber());
        gen.writeEndObject();
    }
}
