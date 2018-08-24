package org.rookit.api.dm.track.title;

import org.rookit.utils.convention.annotation.Property;
import org.rookit.utils.convention.annotation.PropertyContainer;

import java.util.Optional;

@PropertyContainer
public interface Title {

    @Property
    String title();

    Optional<String> artists();

    Optional<String> extras();

    Optional<String> features();

    Optional<String> hiddenTrack();
}
