
package org.rookit.api.storage.update;

import org.rookit.api.dm.genre.GenreSetter;

@SuppressWarnings("javadoc")
public interface GenreUpdateQuery
        extends PlayableUpdateQuery<GenreUpdateQuery, GenreUpdateFilterQuery>, GenreSetter<GenreUpdateQuery> {

    //

}
