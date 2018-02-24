package org.rookit.api.storage.update;

import org.rookit.api.dm.track.TrackSetter;
import org.rookit.api.dm.track.audio.AudioFeatureSetter;

@SuppressWarnings("javadoc")
public interface TrackUpdateQuery extends GenreableUpdateQuery<TrackUpdateQuery, TrackUpdateFilterQuery>, TrackSetter<TrackUpdateQuery>, AudioFeatureSetter<TrackUpdateQuery> {
	
	//
	
}
