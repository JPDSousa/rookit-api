package org.rookit.api.storage;

import org.rookit.api.dm.genre.Genre;
import org.rookit.api.storage.query.GenreQuery;
import org.rookit.api.storage.update.GenreUpdateFilterQuery;
import org.rookit.api.storage.update.GenreUpdateQuery;
import org.rookit.utils.source.Prototype;

@Prototype
public interface GenreDataStore extends RookitModelDataStore<Genre, GenreQuery,
        GenreUpdateQuery, GenreUpdateFilterQuery> {
}
