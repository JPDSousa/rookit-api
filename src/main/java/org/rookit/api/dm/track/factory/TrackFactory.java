package org.rookit.api.dm.track.factory;

import org.rookit.api.dm.factory.RookitFactory;
import org.rookit.api.dm.track.Track;
import org.rookit.api.dm.track.TypeTrack;
import org.rookit.api.dm.track.TypeVersion;
import org.rookit.api.dm.track.VersionTrack;

@SuppressWarnings("javadoc")
public interface TrackFactory extends RookitFactory<Track> {
	
	Track createOriginalTrack(String title);

	Track createTrack(TypeTrack type, String title, Track original, TypeVersion versionType);

	VersionTrack createVersionTrack(TypeVersion versionType, Track original);
	
	
}
