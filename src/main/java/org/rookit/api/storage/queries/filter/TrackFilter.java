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

package org.rookit.api.storage.queries.filter;

import java.util.regex.Pattern;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.dm.track.Track;

@SuppressWarnings("javadoc")
public interface TrackFilter<Q extends TrackFilter<Q>> extends GenreableFilter<Q>, AudioFeaturesFilter<Q> {

    Q withExplicitLyrics(boolean explicit);

    Q withFeature(Artist artist);

    Q withHiddenTrack(Pattern regex);

    Q withHiddenTrack(String hiddenTrack);

    Q withLyrics(Pattern regex);

    Q withLyrics(String lyrics);

    Q withMainArtist(Artist artist);

    Q withOriginal(String id);

    Q withOriginal(Track track);

    Q withPath(boolean isPathPresent);

    Q withProducer(Artist artist);

    Q withTitle(Pattern regex);

    Q withTitle(String title);

    Q withVersionArtist(Artist artist);

    Q withVersionToken(Pattern regex);

    Q withVersionToken(String token);

}
