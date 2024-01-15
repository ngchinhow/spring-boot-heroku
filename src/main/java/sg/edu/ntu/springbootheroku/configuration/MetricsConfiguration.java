package sg.edu.ntu.springbootheroku.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Configuration
public class MetricsConfiguration {

    @Bean
    public Counter indexVisitCounter(MeterRegistry meterRegistry) {
        return Counter.builder("index.visit.count")
        .register(meterRegistry);
    }

    @Bean
    public Timer indexVisitStoreTimer(MeterRegistry meterRegistry) {
        return Timer.builder("index.visit.store.time")
        .register(meterRegistry);
    }
}