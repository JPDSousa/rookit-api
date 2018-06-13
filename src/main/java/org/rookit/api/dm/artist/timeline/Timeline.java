package org.rookit.api.dm.artist.timeline;

import java.time.LocalDate;
import java.util.Optional;

public interface Timeline {

    Optional<LocalDate> begin();

    Optional<LocalDate> end();
}
