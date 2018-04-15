
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
