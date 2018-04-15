
package org.rookit.api.dm.artist.key;

import org.rookit.api.dm.artist.TypeGroup;

@SuppressWarnings("javadoc")
public interface GroupArtistKeySetter<T> {

    T setGroupType(TypeGroup type);

}
