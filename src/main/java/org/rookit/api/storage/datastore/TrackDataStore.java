package org.rookit.api.storage.datastore;

import org.rookit.api.dm.track.Track;
import org.rookit.api.storage.queries.TrackQuery;
import org.rookit.api.storage.update.TrackUpdateFilterQuery;
import org.rookit.api.storage.update.TrackUpdateQuery;

public interface TrackDataStore extends RookitModelDataStore<Track, TrackQuery,
        TrackUpdateQuery, TrackUpdateFilterQuery> {
}
