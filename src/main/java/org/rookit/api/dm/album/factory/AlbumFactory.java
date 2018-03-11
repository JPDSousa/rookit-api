package org.rookit.api.dm.album.factory;

import java.util.Set;

import org.rookit.api.dm.album.Album;
import org.rookit.api.dm.album.TypeAlbum;
import org.rookit.api.dm.album.TypeRelease;
import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.factory.RookitFactory;

@SuppressWarnings("javadoc")
public interface AlbumFactory extends RookitFactory<Album> {
	
	/**
	 * Creates a new single artist album with the parameters provided.
	 *  
	 * @param title album title
	 * @param type release type
	 * @param artists artists responsible for album release
	 * @return a new album, created with the parameters passed.
	 */
	Album createSingleArtistAlbum(String title, TypeRelease type, Set<Artist> artists);

	/**
	 * Creates a new single artist with the parameters provided. This method should
	 * be used to create a new album from tag data.
	 * 
	 * @param albumTag album tag.
	 * @param albumArtistsTag album artists tag
	 * @return a new album, created with the parameters passed.
	 */
	Album createSingleArtistAlbum(String albumTag, String albumArtistsTag);

	/**
	 * Creates a new single artist with the parameters provided.
	 * 
	 * @param albumTitle title of the album.
	 * @param artists set of artists (album artists).
	 * @return a new album, created with the parameters passed.
	 */
	Album createSingleArtistAlbum(String albumTitle, Set<Artist> artists);

	/**
	 * Creates a new album with the specified type. The album will then be set with the specified
	 * title, release type and artists.
	 * 
	 * If any of these last three parameters is irrelevant to the album (e.g. VA album has no main artists)
	 * the correspondent parameter can be set to null.
	 * 
	 * @param albumType album type.
	 * @param title album title.
	 * @param type release type.
	 * @param artists album artists (only the creators).
	 * @return a newly created Album object, with the parameters passed by parameter.
	 */
	Album createAlbum(TypeAlbum albumType, String title, TypeRelease type, Set<Artist> artists);

	/**
	 * Creates a new various artists album.
	 * 
	 * @param title album title.
	 * @param type release type.
	 * @return a newly created {@link Album} object.
	 */
	Album createVAAlbum(String title, TypeRelease type);

}
