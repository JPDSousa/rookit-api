
package org.rookit.api.dm.play;

import java.util.Optional;

import org.rookit.api.dm.track.audio.AudioFeature;
import org.rookit.api.storage.queries.TrackQuery;

@SuppressWarnings("javadoc")
public interface DynamicPlaylist extends AudioFeature, Playlist, PlaylistSetter<Void> {

    // no methods specific to this type of playlist

    TrackQuery applyQuery(TrackQuery original);

    Optional<Boolean> isOnlyPlayable();

    Void setOnlyPlayable(boolean onlyPlayable);
}
