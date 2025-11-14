package dev.black.simplelysoap;

import dev.black.simplelysoap.config.AsyncSyncConfiguration;
import dev.black.simplelysoap.config.EmbeddedElasticsearch;
import dev.black.simplelysoap.config.EmbeddedKafka;
import dev.black.simplelysoap.config.EmbeddedRedis;
import dev.black.simplelysoap.config.EmbeddedSQL;
import dev.black.simplelysoap.config.JacksonConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { SimpleLySoapApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@EmbeddedRedis
@EmbeddedElasticsearch
@EmbeddedSQL
@EmbeddedKafka
public @interface IntegrationTest {
}
