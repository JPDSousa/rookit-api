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

import java.util.Collection;

import org.rookit.api.dm.artist.Artist;

@SuppressWarnings("javadoc")
public interface TrackSetter<T> {

    T addFeature(Artist artist);

    T addFeatures(Collection<Artist> features);
    
    T addProducer(Artist producer);
    
    T addProducers(Collection<Artist> producers);
    
    T clearFeatures();
    
    T clearProducers();
    
    T removeFeature(Artist artist);

    T removeFeatures(Collection<Artist> features);
    
    T removeProducer(Artist producer);

    T removeProducers(Collection<Artist> producers);
    
    T resetHiddenTrack();
    
    T setAudioContent(byte[] audioContent);
    
    T setExplicit(boolean explicit);
    
    T setFeatures(Collection<Artist> features);
    
    T setHiddenTrack(String hiddenTrack);

    T setLyrics(String lyrics);

    T setProducers(Collection<Artist> producers);

}
