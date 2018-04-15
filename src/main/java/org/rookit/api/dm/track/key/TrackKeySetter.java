
package org.rookit.api.dm.track.key;

import java.util.Collection;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.track.TrackTitle;
import org.rookit.api.dm.track.TypeTrack;

@SuppressWarnings("javadoc")
public interface TrackKeySetter<T> {

    T addMainArtists(Collection<Artist> artists);
    
    T clearMainArtists();

    T removeMainArtists(Collection<Artist> artists);
    
    T setMainArtists(Collection<Artist> artists);
    
    T setTitle(String title);
    
    T setTitle(TrackTitle title);
    
    T setTrackType(TypeTrack trackType);

}
