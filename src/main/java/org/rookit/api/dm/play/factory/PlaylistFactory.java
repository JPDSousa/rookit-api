package org.rookit.api.dm.play.factory;

import org.rookit.api.dm.factory.RookitFactory;
import org.rookit.api.dm.play.DynamicPlaylist;
import org.rookit.api.dm.play.Playlist;
import org.rookit.api.dm.play.StaticPlaylist;

@SuppressWarnings("javadoc")
public interface PlaylistFactory extends RookitFactory<Playlist> {

	String CONTEXT = "Playlist";
	
	StaticPlaylist createStaticPlaylist(String name);
	
	DynamicPlaylist createDynamicPlaylist(String name);

}
