
package org.rookit.api.dm.album.key;

import java.util.Collection;

import org.rookit.api.dm.album.TypeAlbum;
import org.rookit.api.dm.album.TypeRelease;
import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface AlbumKeySetter<T> {

    T addArtists(Collection<Artist> artists);
    
    T clearArtists();
    
    T removeArtists(Collection<Artist> artists);
    
    T setAlbumType(TypeAlbum albumType);
    
    T setArtists(Collection<Artist> artists);

    T setReleaseType(TypeRelease releaseType);

    T setTitle(String title);

}
