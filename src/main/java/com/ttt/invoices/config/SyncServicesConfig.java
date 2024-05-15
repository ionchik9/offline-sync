package com.ttt.invoices.config;

import com.ttt.invoices.service.sync.SyncStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.GenericTypeResolver;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class SyncServicesConfig {

    /**
     * Creates a map of synchronization strategies, keyed by the class type of the entities
     * they manage.
     *
     * @param syncServices a list of synchronization strategy services that implement the SyncStrategy interface.
     * @return an unmodifiable map where each key is a class type of the entity and each value
     * is the corresponding synchronization strategy.
     */
    @Bean
    public Map<Class<?>, SyncStrategy<?>> syncStrategiesMap(List<SyncStrategy<?>> syncServices) {
        return syncServices.stream()
                .collect(Collectors.toUnmodifiableMap(
                        service -> GenericTypeResolver.resolveTypeArgument(service.getClass(), SyncStrategy.class),
                        Function.identity()
                ));
    }
}
