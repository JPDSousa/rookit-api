package org.rookit.api.storage.datastore;

import org.rookit.api.dm.genre.Genre;
import org.rookit.api.storage.queries.GenreQuery;
import org.rookit.api.storage.update.GenreUpdateFilterQuery;
import org.rookit.api.storage.update.GenreUpdateQuery;

public interface GenreDataStore extends RookitModelDataStore<Genre, GenreQuery,
        GenreUpdateQuery, GenreUpdateFilterQuery> {
}
