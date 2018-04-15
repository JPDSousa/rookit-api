
package org.rookit.api.dm.track.factory;

import org.rookit.api.dm.factory.RookitFactory;
import org.rookit.api.dm.track.Track;
import org.rookit.api.dm.track.VersionTrack;
import org.rookit.api.dm.track.key.TrackKey;

@SuppressWarnings("javadoc")
public interface TrackFactory extends RookitFactory<Track, TrackKey> {

    Track createOriginalTrack(TrackKey key);

    VersionTrack createVersionTrack(TrackKey key);

}
