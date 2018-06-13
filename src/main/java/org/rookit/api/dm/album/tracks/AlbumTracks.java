package org.rookit.api.dm.album.tracks;

import com.google.common.collect.Lists;
import org.rookit.api.dm.album.disc.Disc;
import org.rookit.api.dm.track.Track;
import org.rookit.utils.DurationUtils;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface AlbumTracks extends Serializable {

    AlbumTrackSlotsAdapter asSlots();

    Optional<Disc> disc(String discName);

    boolean contains(String discName);

    boolean contains(Track track);

    /**
     * Return a set of discs with the discs on the album.
     *
     * @return a set of the album discs.
     */
    Collection<Disc> getDiscs();

    default Collection<Track> asCollection() {
        final List<Track> tracks = Lists.newArrayListWithCapacity(size());

        for (final Disc disc : getDiscs()) {
            tracks.addAll(disc.asTrackCollection());
        }

        return Collections.unmodifiableCollection(tracks);
    }

    /**
     * Returns the primitive of tracks in the entire album. This method will return
     * the sum of the primitive of track on each of the discs of this album.
     *
     * @return primitive of tracks in the entire album.
     */
    int size();

    default Optional<Duration> duration() {
        return Optional.of(getDiscs().stream()
                .map(Disc::duration)
                .reduce(Duration.ZERO, DurationUtils::plus));
    }

    Stream<Track> stream();

}
