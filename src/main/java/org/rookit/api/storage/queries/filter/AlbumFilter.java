
package org.rookit.api.storage.queries.filter;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.rookit.api.dm.album.TypeAlbum;
import org.rookit.api.dm.album.TypeRelease;
import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface AlbumFilter<Q extends AlbumFilter<Q>> extends GenreableFilter<Q> {

    Q withAnyReleaseType(TypeRelease[] types);

    Q withArtist(Artist artist);

    Q withReleaseDate(LocalDate date);

    Q withReleaseType(TypeRelease type);

    Q withTitle(Pattern regex);

    Q withTitle(String albumTitle);

    Q withType(TypeAlbum type);

}
