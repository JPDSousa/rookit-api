
package org.rookit.api.dm.track;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface VersionTrack extends Track {

    String NO_VERSION_TOKEN = StringUtils.EMPTY;

    Track getOriginal();

    Collection<Artist> getVersionArtists();

    String getVersionToken();

    TypeVersion getVersionType();

}
