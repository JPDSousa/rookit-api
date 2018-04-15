
package org.rookit.api.dm.play.able;

import java.time.Duration;
import java.time.LocalDate;

@SuppressWarnings("javadoc")
public interface PlayableSetter<T> {

    T play();
    
    T setDuration(Duration duration);

    T setLastPlayed(LocalDate lastPlayed);

    T setLastSkipped(LocalDate lastSkipped);
    
    T setPlays(long plays);

    T setSkipped(long skipped);

    T skip();

}
