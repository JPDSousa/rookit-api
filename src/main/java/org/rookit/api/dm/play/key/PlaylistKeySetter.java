
package org.rookit.api.dm.play.key;

import org.rookit.api.dm.play.TypePlaylist;

@SuppressWarnings("javadoc")
public interface PlaylistKeySetter<T> {

    T setName(String name);

    T setPlaylistType(TypePlaylist playlistType);

}
