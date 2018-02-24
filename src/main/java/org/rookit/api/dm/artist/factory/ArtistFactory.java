package org.rookit.api.dm.artist.factory;

import java.util.Set;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.artist.TypeArtist;
import org.rookit.api.dm.factory.RookitFactory;
import org.rookit.utils.print.TypeFormat;

/**
 * Artist
 * @author Joao Sousa (jpd.sousa@campus.fct.unl.pt)
 *
 */
public interface ArtistFactory extends RookitFactory<Artist> {
	
	/**
	 * Creates a new artist with the specified name.
	 * 
	 * @param type artist type
	 * @param artistName artist name
	 * @return a new artist with the name passed as parameter
	 */
	Artist createArtist(TypeArtist type, String artistName);

	/**
	 * Creates a set of feature artists based on the artist tag (ID3v1-2) and album artists tag (ID3v2)
	 * This method assumes that the artists are separated by a semi-colon
	 * 
	 * The method returns the all the artists from the artists' tag that are not in the albumArtists' tag.
	 * 
	 * @param artistTag artist tag (ID3v1) (ex. art1;art2)
	 * @param albumArtistsTag album artists tag (ID3v2) (ex. art1;art2)
	 * @return the set of feature artists
	 */
	Set<Artist> createFeatArtists(String artistTag, String albumArtistsTag);

	Set<Artist> createArtists(String[] artistsName);

	String changeFormat(TypeFormat source, TypeFormat target, String value);

	Set<Artist> getArtistsFromTag(String artistTag);

	Set<Artist> getArtistsFromFormat(String rawArtists);
}
