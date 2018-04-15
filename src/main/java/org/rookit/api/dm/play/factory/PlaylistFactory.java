
package org.rookit.api.dm.play.factory;

import org.rookit.api.dm.factory.RookitFactory;
import org.rookit.api.dm.play.DynamicPlaylist;
import org.rookit.api.dm.play.Playlist;
import org.rookit.api.dm.play.StaticPlaylist;
import org.rookit.api.dm.play.key.PlaylistKey;

@SuppressWarnings("javadoc")
public interface PlaylistFactory extends RookitFactory<Playlist, PlaylistKey> {

    String CONTEXT = "Playlist";

    DynamicPlaylist createDynamicPlaylist(String name);

    StaticPlaylist createStaticPlaylist(String name);

}
