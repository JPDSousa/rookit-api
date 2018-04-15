
package org.rookit.api.dm.track.audio;

import com.google.common.collect.Range;
import com.kekstudio.musictheory.Key;

import java.util.Optional;

@SuppressWarnings("javadoc")
public interface AudioFeature extends AudioFeatureSetter<Void> {

    Range<Short> RANGE_BPM = Range.openClosed(Short.valueOf((short) 0), Short.valueOf((short) 400));
    Range<Double> RANGE_DANCEABILITY = Range.open(Double.valueOf(0.0), Double.valueOf(1.0));
    Range<Double> RANGE_ENERGY = Range.open(Double.valueOf(0.0), Double.valueOf(1.0));
    Range<Double> RANGE_VALENCE = Range.open(Double.valueOf(0.0), Double.valueOf(1.0));

    Optional<Short> getBpm();

    Optional<Double> getDanceability();

    Optional<Double> getEnergy();

    Optional<Key> getTrackKey();

    Optional<Double> getValence();

    Optional<Boolean> isAcoustic();

    Optional<Boolean> isInstrumental();

    Optional<Boolean> isLive();
}
