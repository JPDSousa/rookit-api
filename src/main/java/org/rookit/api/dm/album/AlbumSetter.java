package org.rookit.api.dm.album;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Collection;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.track.Track;

/**
 * An interface with the album setters.
 * 
 * @author Joao Sousa (jpd.sousa@campus.fct.unl.pt)
 * @param <T> return type
 *
 */
public interface AlbumSetter<T> {

	/**
	 * Sets a new title for the album.
	 * 
	 * @param title new title for the album.
	 * @return object to return
	 */
	T setTitle(String title);

	/**
	 * Adds the artist passed by parameter to the set of album authors.
	 * 
	 * @param artist artists to be added to the set of album authors
	 * @return object to return
	 */
	T addArtist(Artist artist);
	
	T addArtists(Collection<Artist> artists);
	
	T removeArtist(Artist artist);
	
	T removeArtists(Collection<Artist> artists);
	
	default T addTrack(TrackSlot slot) {
		return slot.getTrack()
				.map(track -> addTrack(track, slot.getNumber(), slot.getDisc()))
				.orElseThrow(() -> new IllegalArgumentException("The slot is empty."));
	}

	T addTrack(Track track, int i, String discName);
	
	T addTrackLast(Track track, String discName);
	
	T removeTrack(Track track);
	
	T removeTrack(int number, String disc);
	
	T removeAllTracks();
	
	/**
	 * Sets a new release date for the album. The old
	 * date assigned will be overwritten.
	 * @param date new date to be assigned
	 * @return object to return
	 */
	T setReleaseDate(LocalDate date);
	
	/**
	 * Sets a new cover art for the album. The cover art is represented
	 * as a byte array so, in order to read from a JPEG file one should
	 * use {@link Files#readAllBytes(java.nio.file.Path)} as so:
	 * <pre>Files.readAllBytes(Paths.get("jpeg-path-as-string");</pre>
	 * <p>In order to download an image into a byte array:</p>
	 * <pre> 
	 * 	URL u = new URL("http://localhost:8080/images/anImage.jpg");
	 * 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	 * 	try {
	 * 		byte[] chunk = new byte[4096];
	 * 		int bytesRead;
	 * 		InputStream stream = toDownload.openStream();
	 * 
	 * 		while ((bytesRead = stream.read(chunk)) > 0) {
	 * 			outputStream.write(chunk, 0, bytesRead);
	 * 		}
	 * 
	 * 	} catch (IOException e) {
	 * 		e.printStackTrace();
	 * 	}
	 * 	outputStream.toByteArray();//image bytes
	 * </pre>
	 * @param image byte array that represents the image to be set as cover
	 * @return object to return
	 */
	T setCover(byte[] image);

	/**
	 * <b>This method is only used by {@link Track} to relocate tracks when its number and/or disc
	 * are changed. Do not use this method otherwise.</b>
	 * 
	 * This method relocates the track in the album's track list to the disc and number passed as parameter.
	 * 
	 * @param discName source disc name 
	 * @param number source number
	 * @param newDiscName target disc name
	 * @param newNumber target track number
	 * 
	 */
	void relocate(String discName, Integer number, String newDiscName, Integer newNumber);

}
