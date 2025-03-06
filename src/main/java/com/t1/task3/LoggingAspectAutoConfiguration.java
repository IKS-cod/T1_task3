package com.t1.task3;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(LoggingAspect.class)
public class LoggingAspectAutoConfiguration {
}
