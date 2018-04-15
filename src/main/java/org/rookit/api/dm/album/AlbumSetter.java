
package org.rookit.api.dm.album;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Objects;

import org.rookit.api.dm.album.slot.TrackSlot;
import org.rookit.api.dm.track.Track;

/**
 * An interface with the album setters.
 *
 * @author Joao Sousa (jpd.sousa@campus.fct.unl.pt)
 * @param <T>
 *            return type
 *
 */
@SuppressWarnings("javadoc")
public interface AlbumSetter<T> {

    T addTrack(Track track, int i, String discName);

    default T addTrack(final TrackSlot slot) {
        Objects.requireNonNull(slot, "The slot cannot be null");

        return slot.getTrack()
                .map(track -> addTrack(track, slot.getNumber(), slot.getDisc()))
                .orElseThrow(() -> new IllegalArgumentException("The slot is empty."));
    }

    T addTrackLast(Track track, String discName);

    /**
     * <b>This method is only used by {@link Track} to relocate tracks when its
     * number and/or disc are changed. Do not use this method otherwise.</b>
     * 
     * This method relocates the track in the album's track list to the disc and
     * number passed as parameter.
     * 
     * @param discName
     *            source disc name
     * @param number
     *            source number
     * @param newDiscName
     *            target disc name
     * @param newNumber
     *            target track number
     * @return TODO
     * 
     */
    T relocate(String discName, int number, String newDiscName, int newNumber);

    T clearTracks();

    T removeTrack(int number, String disc);

    T removeTrack(Track track);

    /**
     * Sets a new cover art for the album. The cover art is represented as a
     * byte array so, in order to read from a JPEG file one should use
     * {@link Files#readAllBytes(java.nio.file.Path)} as so:
     * 
     * <pre>
     * Files.readAllBytes(Paths.get("jpeg-path-as-string");
     * </pre>
     * <p>
     * In order to download an image into a byte array:
     * </p>
     * 
     * <pre>
     * 
     * URL u = new URL("http://localhost:8080/images/anImage.jpg");
     * ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
     * try {
     *     byte[] chunk = new byte[4096];
     *     int bytesRead;
     *     InputStream stream = toDownload.openStream();
     * 
     *     while ((bytesRead = stream.read(chunk)) > 0) {
     *         outputStream.write(chunk, 0, bytesRead);
     *     }
     * 
     * } catch (IOException e) {
     *     e.printStackTrace();
     * }
     * outputStream.toByteArray();// image bytes
     * </pre>
     * 
     * @param image
     *            byte array that represents the image to be set as cover
     * @return object to return
     */
    T setCover(byte[] image);

    /**
     * Sets a new release date for the album. The old date assigned will be
     * overwritten.
     * 
     * @param date
     *            new date to be assigned
     * @return object to return
     */
    T setReleaseDate(LocalDate date);

}
