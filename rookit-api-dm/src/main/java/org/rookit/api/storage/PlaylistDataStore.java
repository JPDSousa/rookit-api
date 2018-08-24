package org.rookit.api.storage;

import org.rookit.api.dm.play.Playlist;
import org.rookit.api.storage.query.PlaylistQuery;
import org.rookit.api.storage.update.PlaylistUpdateFilterQuery;
import org.rookit.api.storage.update.PlaylistUpdateQuery;
import org.rookit.utils.source.Prototype;

@Prototype
public interface PlaylistDataStore extends RookitModelDataStore<Playlist, PlaylistQuery,
        PlaylistUpdateQuery, PlaylistUpdateFilterQuery> {
}
