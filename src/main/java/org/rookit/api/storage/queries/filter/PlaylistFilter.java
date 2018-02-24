package org.rookit.api.storage.queries.filter;

import org.rookit.api.dm.track.Track;

@SuppressWarnings("javadoc")
public interface PlaylistFilter<Q extends PlaylistFilter<Q>> extends PlayableFilter<Q> {

	Q withName(String name);

	Q withTrack(Track track);

}
