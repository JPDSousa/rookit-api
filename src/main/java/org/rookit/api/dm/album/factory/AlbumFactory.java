
package org.rookit.api.dm.album.factory;

import org.rookit.api.dm.album.Album;
import org.rookit.api.dm.album.key.AlbumKey;
import org.rookit.api.dm.factory.RookitFactory;

@SuppressWarnings("javadoc")
public interface AlbumFactory extends RookitFactory<Album, AlbumKey> {

    /**
     * Creates a new album with the specified type. The album will then be set
     * with the specified title, release type and artists.
     *
     * If any of these last three parameters is irrelevant to the album (e.g. VA
     * album has no main artists) the correspondent parameter can be set to
     * null.
     * @param albumKey TODO
     *
     * @return a newly created Album object, with the parameters passed by
     *         parameter.
     */
    Album createSingleArtistAlbum(AlbumKey albumKey);
    
    Album createVariousArtistsAlbum(AlbumKey albumKey);

}
