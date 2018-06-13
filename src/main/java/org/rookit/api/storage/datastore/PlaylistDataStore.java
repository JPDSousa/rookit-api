package org.rookit.api.storage.datastore;

import org.rookit.api.dm.play.Playlist;
import org.rookit.api.storage.queries.PlaylistQuery;
import org.rookit.api.storage.update.PlaylistUpdateFilterQuery;
import org.rookit.api.storage.update.PlaylistUpdateQuery;

public interface PlaylistDataStore extends RookitModelDataStore<Playlist, PlaylistQuery,
        PlaylistUpdateQuery, PlaylistUpdateFilterQuery> {
}
