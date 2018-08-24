package org.rookit.api.storage;

import org.rookit.api.dm.album.Album;
import org.rookit.api.storage.query.AlbumQuery;
import org.rookit.api.storage.update.AlbumUpdateFilterQuery;
import org.rookit.api.storage.update.AlbumUpdateQuery;
import org.rookit.utils.source.Prototype;

@Prototype
public interface AlbumDataStore extends RookitModelDataStore<Album, AlbumQuery,
        AlbumUpdateQuery, AlbumUpdateFilterQuery> {
}
