
package org.rookit.api.dm.track.key;

import java.util.Collection;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.track.Track;
import org.rookit.api.dm.track.TypeVersion;

@SuppressWarnings("javadoc")
public interface VersionTrackKeySetter<T> {

    T addVersionArtists(Collection<Artist> extraArtists);

    T clearVersionArtists();

    T removeVersionArtists(Collection<Artist> artists);

    T resetVersionToken();

    T setOriginal(Track original);

    T setVersionArtists(Collection<Artist> artists);

    T setVersionToken(String versionToken);

    T setVersionType(TypeVersion versionType);

}
