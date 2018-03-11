package org.rookit.api.dm.track.audio;

import java.util.Optional;

import com.google.common.collect.Range;

@SuppressWarnings("javadoc")
public interface AudioFeature extends AudioFeatureSetter<Void> {

	Range<Short> RANGE_BPM = Range.closedOpen((short) 0, (short) 400);
	Range<Double> RANGE_DANCEABILITY = Range.open(0.0, 1.0);
	Range<Double> RANGE_ENERGY = Range.open(0.0, 1.0);
	Range<Double> RANGE_VALENCE = Range.open(0.0, 1.0);

	Optional<Short> getBPM();
	
	Optional<TrackKey> getTrackKey();
	
	Optional<TrackMode> getTrackMode();
	
	Optional<Boolean> isInstrumental();
	
	Optional<Boolean> isLive();
	
	Optional<Boolean> isAcoustic();
	
	Optional<Double> getDanceability();
	
	Optional<Double> getEnergy();
	
	Optional<Double> getValence();
}
