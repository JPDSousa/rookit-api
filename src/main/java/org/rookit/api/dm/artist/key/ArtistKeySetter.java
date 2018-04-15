
package org.rookit.api.dm.artist.key;

import org.rookit.api.dm.artist.TypeArtist;

@SuppressWarnings("javadoc")
public interface ArtistKeySetter<T> {

    T setArtistType(TypeArtist artistType);

    T setISNI(String isni);

    T setName(String name);

}
