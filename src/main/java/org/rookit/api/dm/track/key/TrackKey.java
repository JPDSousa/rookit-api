package org.rookit.api.dm.track.key;

import java.util.Collection;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.key.Key;
import org.rookit.api.dm.track.TypeTrack;

@SuppressWarnings("javadoc")
public interface TrackKey extends VersionTrackKey, Key {
    
    Collection<Artist> getMainArtists();
    
    TypeTrack getTrackType();
    
    String getTitle();

}
