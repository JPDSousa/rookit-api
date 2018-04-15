
package org.rookit.api.dm.track.audio;

import com.kekstudio.musictheory.Key;

@SuppressWarnings("javadoc")
public interface AudioFeatureSetter<T> {

    T setAcoustic(boolean isAcoustic);

    T setBpm(short bpm);

    T setDanceability(double danceability);

    T setEnergy(double energy);

    T setInstrumental(boolean isInstrumental);

    T setLive(boolean isLive);

    T setTrackKey(Key trackKey);

    T setValence(double valence);

}
