package org.rookit.api.dm.artist.timeline;

import org.rookit.utils.convention.annotation.Property;
import org.rookit.utils.convention.annotation.PropertyContainer;

import java.time.LocalDate;
import java.util.Optional;

@PropertyContainer
public interface Timeline {

    @Property
    Optional<LocalDate> begin();

    @Property
    Optional<LocalDate> end();
}
