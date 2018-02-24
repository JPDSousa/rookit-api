package org.rookit.api.dm.play;

import org.rookit.api.dm.track.audio.AudioFeature;
import org.rookit.api.storage.queries.TrackQuery;

@SuppressWarnings("javadoc")
public interface DynamicPlaylist extends AudioFeature, Playlist, PlaylistSetter<Void> {
	
	// no methods specific to this type of playlist
	
	TrackQuery applyQuery(TrackQuery original);
	
	Void setOnlyPlayable(Boolean onlyPlayable);
}
