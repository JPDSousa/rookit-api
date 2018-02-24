package org.rookit.api.dm.factory;

import org.rookit.api.dm.RookitModel;
import org.rookit.api.dm.album.AlbumFactory;
import org.rookit.api.dm.artist.factory.ArtistFactory;
import org.rookit.api.dm.genre.factory.GenreFactory;
import org.rookit.api.dm.play.factory.PlaylistFactory;
import org.rookit.api.dm.track.factory.TrackFactory;

@SuppressWarnings("javadoc")
public interface RookitFactories {

	<T extends RookitModel> RookitFactory<T> getFactory(Class<T> clazz);
	
	GenreFactory getGenreFactory();
	
	ArtistFactory getArtistFactory();
	
	AlbumFactory getAlbumFactory();
	
	TrackFactory getTrackFactory();
	
	PlaylistFactory getPlaylistFactory();
	
}
