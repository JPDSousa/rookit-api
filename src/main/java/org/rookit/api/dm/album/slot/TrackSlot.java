
package org.rookit.api.dm.album.slot;

import java.io.Serializable;
import java.util.Optional;

import org.rookit.api.dm.track.Track;

@SuppressWarnings("javadoc")
public interface TrackSlot extends Serializable {

    String getDisc();

    int getNumber();

    Optional<Track> getTrack();

    boolean isEmpty();
}
