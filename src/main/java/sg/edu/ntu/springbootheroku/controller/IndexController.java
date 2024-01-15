package sg.edu.ntu.springbootheroku.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.edu.ntu.springbootheroku.entity.IndexVisitCount;
import sg.edu.ntu.springbootheroku.repository.IndexVisitCountRepository;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final Counter indexVisitCounter;
    private final Timer indexVisitStoreTimer;
    private final IndexVisitCountRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getIndex() {
        // Increment the visit count
        indexVisitCounter.increment();
        
        // Create entity, prepare to store into database
        final var indexVisitCount = IndexVisitCount.builder()
        .count((long) indexVisitCounter.count())
        .createdAt(LocalDateTime.now())
        .build();

        // Store into database and time how long it takes using a lambda java.util.function.Supplier
        var storedCount = indexVisitStoreTimer.record(() -> repository.save(indexVisitCount));

        // Create return string to show user
        assert storedCount != null;
        var responseMessage = "Welcome to the home page! You have visited " +
        (int) indexVisitCounter.count() +
        " times, and the latest record ID is " +
        storedCount.getId() + 
        ". On average, your visits took " +
        indexVisitStoreTimer.mean(TimeUnit.MILLISECONDS) +
        " milliseconds to store into the database! Wow!";

        // return response
        return ResponseEntity.ok(responseMessage);
    }
}
