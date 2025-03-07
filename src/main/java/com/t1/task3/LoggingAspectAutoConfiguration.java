package com.t1.task3;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingAspectProperties.class)
public class LoggingAspectAutoConfiguration {

    private final LoggingAspectProperties loggingAspectProperties;

    public LoggingAspectAutoConfiguration(LoggingAspectProperties loggingAspectProperties) {
        this.loggingAspectProperties = loggingAspectProperties;
    }

    @Bean
    @ConditionalOnProperty(name = "log.config.enabled", havingValue = "true", matchIfMissing = true)
    public LoggingAspect loggingAspect() {
        return new LoggingAspect(loggingAspectProperties);
    }
}
