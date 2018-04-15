
package org.rookit.api.dm.artist.key;

import org.rookit.api.dm.artist.TypeArtist;
import org.rookit.api.dm.artist.TypeGender;
import org.rookit.api.dm.artist.TypeGroup;
import org.rookit.api.dm.key.Key;

@SuppressWarnings("javadoc")
public interface ArtistKey extends Key {

    TypeArtist getArtistType();

    String getISNI();

    String getName();

    String getFullName();

    TypeGender getGender();
    
    TypeGroup getGroupType();

}
