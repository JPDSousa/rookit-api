package org.rookit.api.dm.album;

import org.rookit.api.dm.track.Track;

@SuppressWarnings("javadoc")
public interface TrackSlot {

	String getDisc();

	int getNumber();

	Track getTrack();
	
	boolean isEmpty();
}
