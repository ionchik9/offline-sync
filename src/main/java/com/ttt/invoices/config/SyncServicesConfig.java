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

    @Bean
    public Map<Class<?>, SyncStrategy<?>> syncStrategiesMap(List<SyncStrategy<?>> syncServices) {
        return syncServices.stream()
                .collect(Collectors.toMap(
                        service -> GenericTypeResolver.resolveTypeArgument(service.getClass(), SyncStrategy.class),
                        Function.identity()
                ));
    }
}
