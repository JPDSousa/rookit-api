
package org.rookit.api.storage.queries.filter;

import java.util.regex.Pattern;

@SuppressWarnings("javadoc")
public interface GenreFilter<Q extends GenreFilter<Q>> extends PlayableFilter<Q> {

    Q withDescription(Pattern regex);

    Q withDescription(String description);

    Q withName(Pattern regex);

    Q withName(String genreName);

}
