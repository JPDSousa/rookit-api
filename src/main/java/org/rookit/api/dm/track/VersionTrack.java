package org.rookit.api.dm.track;

import java.util.Collection;
import java.util.Optional;

import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface VersionTrack extends Track, VersionTrackSetter<Void> {

	Track getOriginal();

	TypeVersion getVersionType();

	Collection<Artist> getVersionArtists();

	Optional<String> getVersionToken();

}
