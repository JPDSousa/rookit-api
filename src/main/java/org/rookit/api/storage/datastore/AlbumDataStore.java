package org.rookit.api.storage.datastore;

import org.rookit.api.dm.album.Album;
import org.rookit.api.storage.queries.AlbumQuery;
import org.rookit.api.storage.update.AlbumUpdateFilterQuery;
import org.rookit.api.storage.update.AlbumUpdateQuery;

public interface AlbumDataStore extends RookitModelDataStore<Album, AlbumQuery,
        AlbumUpdateQuery, AlbumUpdateFilterQuery> {
}
