package org.rookit.api.dm.track;

import java.util.Collection;
import java.util.Set;

import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface VersionTrackSetter<T> {

	T addVersionArtist(Artist extraArtist);

	T addVersionArtists(Collection<Artist> extraArtists);

	T setVersionArtists(Set<Artist> artists);

	T removeVersionArtist(Artist artist);

	T removeVersionArtists(Collection<Artist> artists);

	T clearVersionArtists();

	T setVersionToken(String versionToken);

	T resetVersionToken();

}