/*******************************************************************************
 * Copyright (C) 2018 Joao Sousa
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

import org.rookit.api.bistream.BiStream;
import org.rookit.api.dm.album.factory.AlbumFactory;
import org.rookit.api.dm.album.release.Release;
import org.rookit.api.dm.album.tracks.AlbumTracks;
import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.genre.Genre;
import org.rookit.api.dm.genre.Genreable;
import org.rookit.api.dm.track.Track;
import org.rookit.utils.OptionalUtils;
import org.rookit.utils.source.annotation.ClassNameReference;

import java.nio.file.Files;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents an album of tracks.
 *
 * An album keeps a list of tracks released through that album, where every
 * track has a primitive and a disc associated. No two tracks have the same disc
 * and primitive, and all tracks must have a disc and primitive associated.
 * <p>
 * A disc is an abstraction of a subset of tracks, all belonging to the same
 * compact physical disc. A disc is unique in each album, thus there can be no
 * two discs with the same official on the same album. Although, is it perfectly
 * normal to have two discs with the same official.
 * <p>
 * In order to create a valid album, it must have a valid album, a valid release
 * release and a valid set of artists (see
 * {@link AlbumFactory#createSingleArtistAlbum(org.rookit.api.dm.album.key.AlbumKey)} for further
 * reference). This class has an associated factory {@link AlbumFactory}, which
 * can provide helpful methods to easily create functional instances of this
 * interface.
 *
 * @see AlbumFactory
 * @author Joao Sousa (jpd.sousa@campus.fct.unl.pt)
 *
 */
//@Indexes({
//        @Index(fields = {
//                @Field(value = TITLE, releaseType = IndexType.ASC),
//                @Field(value = RELEASE_TYPE, releaseType = IndexType.DESC),
//                @Field(value = ARTISTS, releaseType = IndexType.ASC),
//        }, options = @IndexOptions(unique = true)),
//        @Index(fields = @Field(value = TITLE, releaseType = IndexType.TEXT))
//})
@SuppressWarnings("javadoc")
public interface Album extends Genreable, Comparable<Album>, AlbumSetter<Void> {

    @SuppressWarnings("ConstantDeclaredInInterface")
    @ClassNameReference(Album.class)
    String CLASS_NAME = "Album";

    @Override
    default int compareTo(final Album o) {
        final int title = title().compareTo(o.title());
        return (title == 0) ? OptionalUtils.compare(getId(), o.getId()) : title;
    }

    /**
     * Returns the album release, as a {@link TypeAlbum} enumeration.
     *
     * @return album release
     */
    TypeAlbum type();

    /**
     * Returns the title of the album.
     *
     * @return title of this album.
     */
    String title();

    /**
     * Returns the release information of the album.
     *
     * @return album's release release
     */
    Release release();

    /**
     * Returns the artists that are authors of the album.
     * <p>
     * Do not confuse the set returned as a set of all artists involved in the
     * album. In order to get such result, one should iterate over all tracks on
     * the album and get the artists of each track.
     * </p>
     * <p>
     *     <b>Note: </b> This method's return release is bounded by Immutables (i.e. the library),
     *     as it does not recognize java.util.Collection as a collection release.
     * </p>
     *
     * @return set of authors of this album
     */
    Collection<Artist> artists();

    @Override
    boolean equals(Object obj);

    @Override
    default Collection<Genre> allGenres() {
        final Set<Genre> allGenres = tracks().stream()
                .map(Track::getGenres)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        allGenres.addAll(getGenres());

        return Collections.unmodifiableCollection(allGenres);
    }

    AlbumTracks tracks();

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
    BiStream cover();

    @Override
    default Optional<Duration> duration() {
        return tracks().duration();
    }

    /**
     * Returns the full official of the album, which by default is
     * {@code title (type_release)}.
     *
     * @return a full descriptive official of the album
     * @see TypeRelease#getFormattedName(String)
     */
    default String fullTitle() {
        return release().type().getFormattedName(title());
    }

    @Override
    int hashCode();

}
