package sg.edu.ntu.springbootheroku.configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {

    @Bean
    public Counter indexVisitCounter(MeterRegistry meterRegistry) {
        return Counter.builder("index.visit.count")
            .description("Counts the number of times the index page was accessed")
            .register(meterRegistry);
    }

    @Bean
    public Timer indexVisitStoreTimer(MeterRegistry meterRegistry) {
        return Timer.builder("index.store.time")
            .description("Measures how long it takes to store a record into the database")
            .register(meterRegistry);
    }
}
