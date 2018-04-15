
package org.rookit.api.storage.queries.filter;

import java.time.Duration;
import java.time.LocalDate;

@SuppressWarnings("javadoc")
public interface PlayableFilter<Q extends PlayableFilter<Q>> extends RookitFilter<Q> {

    Q lastPlayedAfter(LocalDate date);
    
    Q lastPlayedBefore(LocalDate date);
    
    Q lastSkippedAfter(LocalDate date);

    Q lastSkippedBefore(LocalDate date);
    
    Q playedBetween(long min, long max);

    Q playedLessThan(long plays);
    
    Q playedMoreThan(long plays);
    
    Q skippedBetween(long min, long max);

    Q skippedLessThan(long skipped);
    
    Q skippedMoreThan(long skipped);

    Q withDurationBetween(Duration min, Duration max);
    
    Q withDurationGreaterThan(Duration duration);
    
    Q withDurationSmallerThan(Duration duration);

}
