package sg.edu.ntu.springbootheroku.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.ntu.springbootheroku.entity.IndexVisitCount;
import sg.edu.ntu.springbootheroku.repository.IndexVisitCountRepository;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final Counter indexVisitCounter;
    private final Timer indexVisitStoreTimer;
    private final IndexVisitCountRepository repository;

    @GetMapping("/")
    public ResponseEntity<String> getIndex() {
        indexVisitCounter.increment();
        final var indexVisitCount = IndexVisitCount.builder()
            .count((long) indexVisitCounter.count())
            .createdAt(LocalDateTime.now())
            .build();
        var storedCount = indexVisitStoreTimer.record(() -> repository.save(indexVisitCount));
        assert storedCount != null;
        var responseMessage = "Welcome to the home page! You have visited " +
            (int) indexVisitCounter.count() +
            " times, and the latest record ID is " +
            storedCount.getId() +
            ". On average, your visits took " +
            indexVisitStoreTimer.mean(TimeUnit.MILLISECONDS) +
            " milliseconds to store into the database! Wow!";
        return ResponseEntity.ok(responseMessage);
    }
}
