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
package org.rookit.api.dm.artist;

import static org.rookit.api.dm.artist.ArtistFields.ISNI;
import static org.rookit.api.dm.artist.ArtistFields.NAME;
import static org.rookit.api.dm.artist.ArtistFields.TYPE;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;
import org.rookit.api.bistream.BiStream;
import org.rookit.api.dm.genre.Genreable;
import org.rookit.api.dm.play.able.Playable;

@SuppressWarnings("javadoc")
@Indexes({
        @Index(fields = {
                @Field(value = NAME, type = IndexType.ASC),
                @Field(value = TYPE, type = IndexType.ASC),
                @Field(value = ISNI, type = IndexType.ASC)
        }, options = @IndexOptions(unique = true)),
        @Index(fields = @Field(value = NAME, type = IndexType.TEXT))
})
// TODO is it possible to turn this into Artist.class.getName() somehow??
@Entity(value = "Artist")
public interface Artist extends Genreable, Playable, Comparable<Artist>, ArtistSetter<Void> {

    /**
     * String representation of an unknown artist. This constant may be used
     * only when the artist of a track or album is required but unknown.
     */
    String UNKNOWN_ARTISTS = "Unknown Artists";

    String UNKNOWN_ISNI = "_isni_";

    public Collection<String> getAliases();

    public Optional<LocalDate> getBeginDate();

    public Optional<LocalDate> getEndDate();

    public Optional<String> getIPI();

    public String getISNI();

    /**
     * Returns the artist name
     * 
     * @return artist name as a string.
     */
    public String getName();

    /**
     * Returns the origin of this artist (location where the artist came from)
     * 
     * @return the artist's origin
     */
    public Optional<String> getOrigin();

    public BiStream getPicture();

    /**
     * Returns the set of artists related to this artist
     * 
     * @return set of artists related to this artist
     */
    public Collection<Artist> getRelatedArtists();

    public TypeArtist getType();
}
