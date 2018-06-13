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

package org.rookit.api.dm.track;

import org.rookit.api.dm.genre.Genreable;
import org.rookit.api.dm.track.artist.TrackArtists;
import org.rookit.api.dm.track.audio.AudioContent;
import org.rookit.api.dm.track.lyrics.Lyrics;
import org.rookit.api.dm.track.title.TrackTitle;
import org.rookit.utils.OptionalUtils;

import java.util.Optional;

@SuppressWarnings("javadoc")
// @Index(fields = @Field(value = TITLE, release = IndexType.TEXT), options
// = @IndexOptions(
// disableValidation = true))
//@Indexes(@Index(fields = {
//        @Field(value = TITLE, releaseType = IndexType.ASC),
//        @Field(value = MAIN_ARTISTS, releaseType = IndexType.ASC),
//        @Field(value = TYPE, releaseType = IndexType.ASC),
//        @Field(value = VERSION_TYPE, releaseType = IndexType.ASC),
//        @Field(value = VERSION_ARTISTS, releaseType = IndexType.ASC),
//},
//        options = @IndexOptions(
//                unique = true,
//                disableValidation = true)))
public interface Track extends Genreable, Comparable<Track>, TrackSetter<Void> {

    @Override
    default int compareTo(final Track o) {
        final int title = title().toString().compareTo(o.title().toString());
        return (title == 0) ? OptionalUtils.compare(getId(), o.getId()) : title;
    }

    Optional<VersionTrack> asVersionTrack();

    AudioContent audio();

    TrackArtists artists();

    Lyrics lyrics();

    TrackTitle title();

    TypeTrack type();

    boolean isVersionTrack();

}
