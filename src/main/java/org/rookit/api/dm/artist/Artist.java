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

import org.rookit.api.dm.artist.profile.Profile;
import org.rookit.api.dm.genre.Genreable;
import org.rookit.utils.OptionalUtils;

import java.util.Collection;

@SuppressWarnings("javadoc")
//@Indexes({
//        @Index(fields = {
//                @Field(value = NAME, releaseType = IndexType.ASC),
//                @Field(value = TYPE, releaseType = IndexType.ASC),
//                @Field(value = ISNI, releaseType = IndexType.ASC)
//        }, options = @IndexOptions(unique = true)),
//        @Index(fields = @Field(value = NAME, releaseType = IndexType.TEXT))
//})
public interface Artist extends Genreable, Comparable<Artist>, ArtistSetter<Void> {

    @Override
    default int compareTo(final Artist o) {
        final int profile = profile().compareTo(o.profile());
        return (profile == 0) ? OptionalUtils.compare(getId(), o.getId()) : profile;
    }

    Profile profile();

    /**
     * Returns the set of artists related to this artist
     * 
     * @return set of artists related to this artist
     */
    Collection<Artist> relatedArtists();

    TypeArtist type();
}
