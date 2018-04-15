
package org.rookit.api.storage.update;

import org.rookit.api.storage.queries.filter.GenreableFilter;

@SuppressWarnings("javadoc")
public interface GenreableUpdateFilterQuery<Q extends GenreableUpdateFilterQuery<Q>>
        extends GenreableFilter<Q>, PlayableUpdateFilterQuery<Q> {

    //

}
