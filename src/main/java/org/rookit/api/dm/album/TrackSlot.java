package org.rookit.api.dm.album;

import java.util.Optional;

import org.rookit.api.dm.track.Track;

@SuppressWarnings("javadoc")
public interface TrackSlot {

	String getDisc();

	int getNumber();

	Optional<Track> getTrack();
	
	boolean isEmpty();
}
