package org.rookit.api.dm.play.able;

import java.time.Duration;
import java.time.LocalDate;

@SuppressWarnings("javadoc")
public interface PlayableSetter<T> {

	T play();
	T setPlays(long plays);

	T setLastPlayed(LocalDate lastPlayed);

	T skip();
	T setSkipped(long skipped);

	T setLastSkipped(LocalDate lastSkipped);

	T setDuration(Duration duration);
	
}
