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

import static org.rookit.api.dm.track.TrackFields.MAIN_ARTISTS;
import static org.rookit.api.dm.track.TrackFields.TITLE;
import static org.rookit.api.dm.track.TrackFields.TYPE;
import static org.rookit.api.dm.track.TrackFields.VERSION_ARTISTS;
import static org.rookit.api.dm.track.TrackFields.VERSION_TYPE;

import java.util.Collection;
import java.util.Optional;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;
import org.rookit.api.bistream.BiStream;
import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.genre.Genreable;
import org.rookit.api.dm.play.able.Playable;
import org.rookit.api.dm.track.audio.AudioFeature;

@SuppressWarnings("javadoc")
@Entity("Track")
@Indexes({
        @Index(fields = {
                @Field(value = TITLE, type = IndexType.ASC),
                @Field(value = MAIN_ARTISTS, type = IndexType.ASC),
                @Field(value = TYPE, type = IndexType.ASC),
                @Field(value = VERSION_TYPE, type = IndexType.ASC),
                @Field(value = VERSION_ARTISTS, type = IndexType.ASC),
        },
                options = @IndexOptions(
                        unique = true,
                        disableValidation = true)),
        // @Index(fields = @Field(value = TITLE, type = IndexType.TEXT), options
        // = @IndexOptions(
        // disableValidation = true))
})
public interface Track extends AudioFeature, Playable, Genreable, Comparable<Track>, TrackSetter<Void> {

    Optional<VersionTrack> getAsVersionTrack();
    
    BiStream getAudioContent();
    
    Collection<Artist> getFeatures();

    TrackTitle getFullTitle();
    
    Optional<String> getHiddenTrack();
    
    TrackTitle getLongFullTitle();

    Optional<String> getLyrics();

    Collection<Artist> getMainArtists();
    
    Collection<Artist> getProducers();

    TrackTitle getTitle();

    TypeTrack getType();

    Boolean isExplicit();

    boolean isVersionTrack();

}
