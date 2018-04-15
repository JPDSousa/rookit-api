
package org.rookit.api.storage.update;

import org.rookit.api.dm.artist.ArtistSetter;
import org.rookit.api.dm.artist.key.ArtistKeySetter;

@SuppressWarnings("javadoc")
public interface ArtistUpdateQuery extends GenreableUpdateQuery<ArtistUpdateQuery, ArtistUpdateFilterQuery>,
        ArtistSetter<ArtistUpdateQuery>, ArtistKeySetter<ArtistUpdateQuery> {

    //
}
