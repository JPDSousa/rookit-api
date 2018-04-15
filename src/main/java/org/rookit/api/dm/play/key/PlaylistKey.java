package org.rookit.api.dm.play.key;

import org.rookit.api.dm.key.Key;
import org.rookit.api.dm.play.TypePlaylist;

@SuppressWarnings("javadoc")
public interface PlaylistKey extends Key {
    
    TypePlaylist getPlaylistType();
    
    String getName();

}
