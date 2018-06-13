package org.rookit.api.storage.datastore;

import org.rookit.api.dm.artist.Artist;
import org.rookit.api.storage.queries.ArtistQuery;
import org.rookit.api.storage.update.ArtistUpdateFilterQuery;
import org.rookit.api.storage.update.ArtistUpdateQuery;

public interface ArtistDataStore extends RookitModelDataStore<Artist, ArtistQuery,
        ArtistUpdateQuery, ArtistUpdateFilterQuery> {

}
