
package org.rookit.api.dm.track.key;

import java.io.Serializable;
import java.util.Collection;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.track.Track;
import org.rookit.api.dm.track.TypeVersion;

@SuppressWarnings("javadoc")
public interface VersionTrackKey extends Serializable {

    Track getOriginal();

    String getVersionToken();

    Collection<Artist> getVersionArtists();

    TypeVersion getVersionType();

}
