
package org.rookit.api.dm.artist.factory;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.artist.GroupArtist;
import org.rookit.api.dm.artist.Musician;
import org.rookit.api.dm.artist.key.ArtistKey;
import org.rookit.api.dm.factory.RookitFactory;

@SuppressWarnings("javadoc")
public interface ArtistFactory extends RookitFactory<Artist, ArtistKey> {

    GroupArtist createGroupArtist(ArtistKey key);

    Musician createMusician(ArtistKey key);

}
