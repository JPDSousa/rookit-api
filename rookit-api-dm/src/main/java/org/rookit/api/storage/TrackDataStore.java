package org.rookit.api.storage;

import org.rookit.api.dm.track.Track;
import org.rookit.api.storage.query.TrackQuery;
import org.rookit.api.storage.update.TrackUpdateFilterQuery;
import org.rookit.api.storage.update.TrackUpdateQuery;
import org.rookit.utils.source.Prototype;

@Prototype
public interface TrackDataStore extends RookitModelDataStore<Track, TrackQuery, TrackUpdateQuery, TrackUpdateFilterQuery> {
}
