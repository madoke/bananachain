package org.madoke.bananachain.node.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;

/**
 * Adds custom jackson features to the ratpack object mapper
 */
public class JacksonModule extends AbstractModule {
    @Override
    protected void configure() {
        ObjectMapper customObjectMapper = new ObjectMapper();
        customObjectMapper.registerModule(new JavaTimeModule());
        bind(ObjectMapper.class).toInstance(customObjectMapper);
    }
}
