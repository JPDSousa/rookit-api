package org.rookit.api.dm.track.title;

import java.util.Optional;

public interface Title {

    String title();

    Optional<String> artists();

    Optional<String> extras();

    Optional<String> features();

    Optional<String> hiddenTrack();
}
