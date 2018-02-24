package org.rookit.api.dm.track;

import java.util.Collection;
import java.util.Set;

import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface VersionTrack extends Track {

	Track getOriginal();

	TypeVersion getVersionType();

	Collection<Artist> getVersionArtists();

	void addVersionArtist(Artist extraArtist);

	void setVersionArtists(Set<Artist> artists);

	void setVersionToken(String versionToken);

	String getVersionToken();

}
