package org.rookit.api.storage;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.storage.query.ArtistQuery;
import org.rookit.api.storage.update.ArtistUpdateFilterQuery;
import org.rookit.api.storage.update.ArtistUpdateQuery;
import org.rookit.utils.source.Prototype;

@Prototype
public interface ArtistDataStore extends RookitModelDataStore<Artist, ArtistQuery,
        ArtistUpdateQuery, ArtistUpdateFilterQuery> {

}
