package org.rookit.api.dm.album.disc;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import org.rookit.api.dm.album.slot.TrackSlot;
import org.rookit.api.dm.track.Track;
import org.rookit.utils.DurationUtils;
import org.rookit.utils.convention.annotation.Property;
import org.rookit.utils.convention.annotation.PropertyContainer;

import java.time.Duration;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@PropertyContainer
public interface Disc extends Iterable<Track> {

    @Property
    String name();

    Optional<Track> track(int number);

    TrackSlot trackAsSlot(int number);

    int size();

    default Duration duration() {
        return asTrackCollection()
                .stream()
                .map(Track::duration)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(Duration.ZERO, DurationUtils::plus);
    }

    @Property
    Int2ObjectMap<Track> trackMap();

    Collection<Track> asTrackCollection();

    Collection<TrackSlot> asTrackSlotsCollection();

    boolean contains(Track track);

    boolean contains(int trackNumber);

    Stream<Track> stream();

}
