
package org.rookit.api.storage.update;

import org.rookit.api.dm.album.AlbumSetter;
import org.rookit.api.dm.album.key.AlbumKeySetter;

@SuppressWarnings("javadoc")
public interface AlbumUpdateQuery extends GenreableUpdateQuery<AlbumUpdateQuery, AlbumUpdateFilterQuery>,
        AlbumSetter<AlbumUpdateQuery>, AlbumKeySetter<AlbumUpdateQuery> {

    //

}
