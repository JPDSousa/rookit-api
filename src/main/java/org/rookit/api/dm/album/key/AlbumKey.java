
package org.rookit.api.dm.album.key;

import java.util.Collection;

import org.rookit.api.dm.album.TypeAlbum;
import org.rookit.api.dm.album.TypeRelease;
import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.key.Key;

@SuppressWarnings("javadoc")
public interface AlbumKey extends Key {

    TypeAlbum getAlbumType();

    String getTitle();

    TypeRelease getType();

    Collection<Artist> getArtists();

}
