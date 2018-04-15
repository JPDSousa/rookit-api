/*******************************************************************************
 * Copyright (C) 2017 Joao Sousa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/

package org.rookit.api.dm.album;

import static org.rookit.api.dm.album.AlbumFields.ARTISTS;
import static org.rookit.api.dm.album.AlbumFields.RELEASE_TYPE;
import static org.rookit.api.dm.album.AlbumFields.TITLE;

import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;
import org.rookit.api.bistream.BiStream;
import org.rookit.api.dm.album.factory.AlbumFactory;
import org.rookit.api.dm.album.slot.TrackSlot;
import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.genre.Genre;
import org.rookit.api.dm.genre.Genreable;
import org.rookit.api.dm.play.able.Playable;
import org.rookit.api.dm.track.Track;
import org.rookit.utils.DurationUtils;

/**
 * Represents an album of tracks.
 *
 * An album keeps a list of tracks released through that album, where every
 * track has a number and a disc associated. No two tracks have the same disc
 * and number, and all tracks must have a disc and number associated.
 * <p>
 * A disc is an abstraction of a subset of tracks, all belonging to the same
 * compact physical disc. A disc is unique in each album, thus there can be no
 * two discs with the same name on the same album. Although, is it perfectly
 * normal to have two discs with the same name.
 * <p>
 * In order to create a valid album, it must have a valid album, a valid release
 * type and a valid set of artists (see
 * {@link AlbumFactory#createSingleArtistAlbum(String, TypeRelease)} for further
 * reference). This class has an associated factory {@link AlbumFactory}, which
 * can provide helpful methods to easily create functional instances of this
 * interface.
 *
 * @see AlbumFactory
 * @since 1
 * @author Joao Sousa (jpd.sousa@campus.fct.unl.pt)
 *
 */
@Indexes({
        @Index(fields = {
                @Field(value = TITLE, type = IndexType.ASC),
                @Field(value = RELEASE_TYPE, type = IndexType.DESC),
                @Field(value = ARTISTS, type = IndexType.ASC),
        }, options = @IndexOptions(unique = true)),
        @Index(fields = @Field(value = TITLE, type = IndexType.TEXT))
})
// TODO is it possible to turn this into Album.class.getName() somehow??
@Entity(value = "Album")
@SuppressWarnings("javadoc")
public interface Album extends Genreable, Playable, Comparable<Album>, AlbumSetter<Void> {

    /**
     * This value represents the name of the disc when the album has only one
     * (nameless) disc.
     */
    String DEFAULT_DISC = "1"; // TODO move this to album factory, load from
                               // configuration file

    /**
     * Value for a track without number
     */
    int NUMBERLESS = 0;

    @Override
    default int compareTo(final Album o) {
        final int title = getTitle().compareTo(o.getTitle());
        return title == 0 ? getIdAsString().compareTo(o.getIdAsString()) : title;
    }

    /**
     * Searches for the track number passed as parameter in the disc (also
     * passed as parameter).
     * 
     * @param disc
     *            disc in which the track number will be searched.
     * @param track
     *            track number to search
     * @return <code>true</code> if both the disc and the track exist and
     *         <code>false</code> if one of the elements does not exist.
     */
    boolean contains(String disc, int track);

    /**
     * Searches for the track passed as parameter in the album's tracks.
     * 
     * @param track
     *            track to search
     * @return <code>true</code> if the track exists in the album and
     *         <code>false</code> otherwise.
     */
    default boolean contains(final Track track) {
        Objects.requireNonNull(track);

        return getTracks().stream()
                .map(TrackSlot::getTrack)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(track::equals)
                .findFirst()
                .isPresent();
    }

    default boolean contains(final TrackSlot slot) {
        return contains(slot.getDisc(), slot.getNumber());
    }

    @Override
    boolean equals(Object obj);

    /**
     * Returns the album type, as a {@link TypeAlbum} enumeration.
     * 
     * @return album type
     */
    TypeAlbum getAlbumType();

    @Override
    default Collection<Genre> getAllGenres() {
        final Set<Genre> allGenres = getTracks().stream()
                .map(TrackSlot::getTrack)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Track::getGenres)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        allGenres.addAll(getGenres());

        return Collections.unmodifiableCollection(allGenres);
    }
    
    /**
     * Returns the artists that are authors of the album.
     * <p>
     * Do not confuse the set returned as a set of all artists involved in the
     * album. In order to get such result, one should iterate over all tracks on
     * the album and get the artists of each track.
     * </p>
     * <p>
     * To add artists to an album only as authors, use the method
     * {@link #addArtist(Artist)}
     * </p>
     * 
     * @return set of authors of this album
     */
    Collection<Artist> getArtists();

    /**
     * Returns the cover art of the album as a byte array.
     * 
     * <p>
     * Use
     * {@link Files#write(java.nio.file.Path, byte[], java.nio.file.OpenOption...)}
     * in order to write the returned bytes to a file (ex: jpeg).
     * 
     * @return a byte array representative of the cover image.
     */
    BiStream getCover();

    /**
     * Return the number of discs in the album.
     * 
     * @return number of discs in the album.
     */
    default int getDiscCount() {
        return getDiscs().size();
    }

    /**
     * Return a set of discs with the discs on the album.
     * 
     * @return a set of the album discs.
     */
    Collection<String> getDiscs();

    @Override
    default Optional<Duration> getDuration() {
        return Optional.of(getDiscs().stream()
                .map(this::getDuration)
                .reduce(Duration.ZERO, DurationUtils::plus));
    }

    /**
     * Returns the total duration of the disc in seconds. The length of the disc
     * should be the sum of the length of all the tracks in the disc.
     * <p>
     * If the disc passed as parameter does not exist in the album,
     * {@link IllegalArgumentException} will the thrown.
     * 
     * @param cd
     *            name of the disc
     * @return the total length of the disc in seconds.
     */
    Duration getDuration(String cd);

    /**
     * Returns the full name of the album, which by default is
     * {@code title (type_release)}.
     * 
     * @return a full descriptive name of the album
     * @see TypeRelease#getFormattedName(String)
     */
    default String getFullTitle() {
        return getReleaseType().getFormattedName(getTitle());
    }

    /**
     * Returns the date of the release of this album.
     * 
     * @return release date of the album.
     */
    Optional<LocalDate> getReleaseDate();

    /**
     * Returns the type of release of the album.
     * 
     * @return album's type release
     */
    TypeRelease getReleaseType();

    default Optional<TrackSlot> getSlot(final Track track) {
        Objects.requireNonNull(track, "Track cannot be null");

        return getTracks().stream()
                .filter(slot -> !slot.isEmpty())
                .filter(trackSlot -> trackSlot.getTrack().get().equals(track))
                .findFirst();
    }

    /**
     * Returns the title of the album.
     * 
     * @return title of this album.
     */
    String getTitle();

    TrackSlot getTrack(String discName, int number);

    default Optional<String> getTrackDisc(final Track track) {
        return getSlot(track).map(TrackSlot::getDisc);
    }

    default Optional<Integer> getTrackNumber(final Track track) {
        return getSlot(track).map(TrackSlot::getNumber);
    }

    Collection<Integer> getTrackNumbers(String cd);

    /**
     * Returns the list of tracks with all the tracks from all the discs.
     * 
     * @return a list of tracks with all the tracks of the album
     */
    Collection<TrackSlot> getTracks();

    /**
     * Returns the set of tracks from the disc passed as parameter.
     * 
     * @param cd
     *            disc name
     * @return the list of tracks from the disc passed as parameter
     */
    Collection<TrackSlot> getTracks(String cd);

    /**
     * Returns the number of tracks in the entire album. This method will return
     * the sum of the number of track on each of the discs of this album.
     * <p>
     * In order to get the number of tracks from a specific disc use
     * {@link #getTracksCount(String)}
     * 
     * @return number of tracks in the entire album.
     */
    int getTracksCount();

    /**
     * Returns the number of tracks in a specific disc passed as parameter. If
     * the disc does not exist on the album, an exception
     * {@link IllegalArgumentException} will be thrown.
     * 
     * @param cd
     *            name of the disc
     * @return number of tracks on the disc passed as parameter
     */
    int getTracksCount(String cd);

    @Override
    int hashCode();

}
