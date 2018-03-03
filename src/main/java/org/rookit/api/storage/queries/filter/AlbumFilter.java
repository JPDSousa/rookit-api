package org.rookit.api.storage.queries.filter;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.rookit.api.dm.album.TypeAlbum;
import org.rookit.api.dm.album.TypeRelease;
import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface AlbumFilter<Q extends AlbumFilter<Q>> extends GenreableFilter<Q> {
	
	Q withTitle(String albumTitle);
	Q withTitle(Pattern regex);

	Q withArtist(Artist artist);
	
	Q withType(TypeAlbum type);

	Q withReleaseType(TypeRelease type);
	Q withAnyReleaseType(TypeRelease[] types);
	
	Q withReleaseDate(LocalDate date);

}