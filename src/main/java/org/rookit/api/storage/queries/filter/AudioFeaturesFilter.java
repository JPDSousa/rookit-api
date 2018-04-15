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

import com.google.common.collect.Range;
import com.kekstudio.musictheory.Key;

@SuppressWarnings("javadoc")
public interface AudioFeaturesFilter<Q> {

    Q withAcoustic(boolean acoustic);

    Q withBPM(Range<Short> range);

    Q withBPM(short bpm);

    Q withBPM(short min, short max);

    Q withDanceability(double danceability);

    Q withDanceability(double min, double max);

    Q withDanceability(Range<Double> range);

    Q withEnergy(double energy);

    Q withEnergy(double min, double max);
    
    Q withEnergy(Range<Double> range);
    
    Q withInstrumental(boolean instrumental);

    Q withLive(boolean live);
    
    Q withTrackKey(Key key);
    
    Q withValence(double valence);
    
    Q withValence(double min, double max);
    
    Q withValence(Range<Double> range);

}
